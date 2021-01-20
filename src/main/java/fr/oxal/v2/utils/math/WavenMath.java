package fr.oxal.v2.utils.math;

import com.google.gson.*;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.entity.pvm.equipment.ring.Ring;
import fr.oxal.v2.waven.entity.pvm.skill.WithElementarySkills;

import java.io.*;
import java.util.Optional;

import static fr.oxal.v2.waven.entity.pvm.skill.WithElementarySkills.KEY;

public class WavenMath {

    public static final String REF_ID = "referenceId";
    public static final String REF_NAME = "valueRefName";
    public static final String CONST_VALUE = "ConstIntegerValue";
    public static final String SPELL_COUNT_VALUE = "SpellsCountValue";
    public static final String FUNCTION_LINEAR = "LinearLevelOnlyBasedDynamicValue";
    public static final String FUNCTION_MULT_SCALING = "MultiplyScaleLevelOnlyBasedDynamicValue";
    public static final String FUNCTION_LIN_SCALING = "LinearScaleLevelOnlyBasedDynamicValue";
    public static final String FUNCTION_SUM = "SumValue";
    public static final String SPELL_EVOLVE = "ThisSpellEvolutionValue";
    public static final String FUNCTION_RANGE_VALUE_RING = "ThisRingRangeDynamicValue";
    public static final String FACTOR = "factor";
    public static final String BASE_VALUE = "baseValue";
    public static final String JSON = "m_jsonRepresentation";
    public static final String FIGHT = "fightConstants";
    public static final String NEGATIVE_VALUE = "NegativeValue";
    public static final String VALUE_TO_NEGATIVE = "valueToNeg";
    public static final String COMPA_SCALE = "companionStatScalingFactor";
    public static final String SPELL_MONSTER_SCALING = "monsterSpellScalingFactor";
    public static final String INVOC_SCALE = "summoningStatScalingFactor";
    public static final String MONSTER_SCALE = "monsterStatScalingFactor";
    public static final String PER_RANGE_LEVEL = "PerRangeLevelOnlyBasedDynamicValue";
    private static final String TYPE = "type";
    private static final String VALUE = "value";
    private static final String VALUES = "values";

    //todo surement a refaire

    public static int getNumber(JsonObject j, int level, WavenInterface w) {
        if (j == null) return -1000;
        System.out.println(j);
        if (j.get(TYPE).getAsString().equals(FUNCTION_LINEAR)) {
            return linear(j, level);
        } else if (j.get(TYPE).getAsString().equals(FUNCTION_MULT_SCALING)) {
            return scalingMult(j, level);
        } else if (j.get(TYPE).getAsString().equals(FUNCTION_LIN_SCALING)) {
            return scaling(j, level);
        } else if (j.get(TYPE).getAsString().equals(FUNCTION_RANGE_VALUE_RING)) {
            return ringRange(level, w, j);
        } else if (j.get(TYPE).getAsString().equals(FUNCTION_SUM)) {
            return sum(j);
        } else if (j.get(TYPE).getAsString().equals(CONST_VALUE)) {
            return j.get(VALUE).getAsInt();
        } else if (j.get(TYPE).getAsString().equals(SPELL_COUNT_VALUE)) {
            return -1000;
        } else if (j.get(TYPE).getAsString().equals(NEGATIVE_VALUE)) {
            return getNumber((JsonObject) j.get(VALUE_TO_NEGATIVE), level, w);
        } else if (j.get(TYPE).getAsString().equals(SPELL_EVOLVE)) {
            return 0;
        } else if (j.get(TYPE).getAsString().equals(PER_RANGE_LEVEL)) {
            return dynamicValuePerRange(level, j.get(VALUES).getAsJsonArray(), w);
        } else {
            System.err.println("erreur wavenMath getNumber");
            //j.forEach((k, v) -> System.out.println(k + " : " + v));
            System.err.println(j.get(TYPE));
        }
        return -1000;
    }

    private static int dynamicValuePerRange(int level, JsonArray jsonArray, WavenInterface w) {
        int value = 0;
        for (JsonElement e : jsonArray) {
            if (e.isJsonObject() && e.getAsJsonObject().has(KEY)) {
                JsonObject j = e.getAsJsonObject();
                if (j.get(KEY).isJsonPrimitive() && j.get(KEY).getAsInt() <= level) {
                    value = getNumber(j.get(WithElementarySkills.VALUE).getAsJsonObject(), level, w);
                }
            }
        }
        return value;
    }

    private static int sum(JsonObject j) {
        int base = 0;
        JsonArray jsonArray = (JsonArray) j.get("valuesToSum");
        for (Object object : jsonArray) {
            JsonObject json = (JsonObject) object;
            if (json.has(TYPE)) {
                if (json.has(VALUE)) {
                    base = json.get(VALUE).getAsInt();
                }
            }
        }

        return base;
    }

    private static int ringRange(int level, WavenInterface a, JsonObject o) {
        String key = o.get(REF_NAME).getAsString();
        Ring r = a.asRing();
        Optional<Integer> i = r.getArrayValue(key, level);
        Optional<Double> f = r.getFactorRange(key);
        if (i.isPresent() && f.isPresent()) {
            return (int) Math.round(i.get() + i.get() * (((double) r.getMaxRandom().get()) / 100) * f.get() / 10);
        }
        System.err.println("-ERROR- WavenMath : ringRange : " + r.getId() + " : " + i + " : " + key);
        return -1000;
    }

    public static int linear(JsonObject j, int level) {
        double base = j.get(BASE_VALUE).getAsDouble();
        double factor = j.get(FACTOR).getAsDouble();
        return (int) Math.round((base + level * factor));
    }

    public static int scaling(JsonObject j, int level) {
        double base = j.get(BASE_VALUE).getAsDouble();

        System.out.println(level);
        System.out.println(base);
        System.out.println(parserConst(j));
        return (int) Math.round((base + (base * parserConst(j) * (level - 1))));
    }

    public static long getExp(int level) {
        double xp = 400;
        for (int i = 1; i < level; i++) {
            xp *= 1.1;
        }

        return Math.round(xp);
    }

    public static int scalingMult(JsonObject j, int level) {
        double base = j.get(BASE_VALUE).getAsDouble();
        return (int) Math.round(base * Math.pow(parserConst(j) + 1, level - 1));
    }

    public static double parserConst(JsonObject j) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Wavenpedia.constPath))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonObject factorJson = (JsonObject) j.get("factor");
        String name = "";

        if (factorJson.get("reference") instanceof JsonPrimitive) {
            JsonPrimitive p = factorJson.get("reference").getAsJsonPrimitive();
            switch (p.getAsInt()) {
                case 0:
                    name = "monsterStatScalingFactor";
                    break;
                case 1:
                    name = "monsterSpellScalingFactor";
                    break;
                case 2:
                    name = "companionStatScalingFactor";
                    break;
                case 3:
                    name = "summoningStatScalingFactor";
                    break;
                case 4:
                    name = "mechanismStatScalingFactor";
                    break;
            }
        }


        try (Reader reader = br) {
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(reader);
            jsonObject = (JsonObject) jsonObject.get(JSON);
            jsonObject = (JsonObject) jsonObject.get("fightConstants");


            if (jsonObject.has(VALUE)) {
                return jsonObject.get(VALUE).getAsJsonPrimitive().getAsInt();
            } else {
                return jsonObject.get(name).getAsJsonPrimitive().getAsDouble();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return 0;
    }

    public static double calc(double i1, double i2, String symbole) {
        switch (symbole) {
            case "/":
                return i1 / i2;
            case "+":
            case "\\+":
                return i1 + i2;
            case "-":
                return i1 - i2;
            case "*":
            case "\\*":
                return i1 * i2;
            default:
                System.err.println("erreur symbole WavenMath calc : " + symbole);
                return -1000;
        }
    }

//    public static double calc(String str, WavenEntity a, int level){
//        String sym = findSymbole(str);
//        String[] tab = str.split(sym);
//        double i1, i2;
//        if (a instanceof Ring){
//            i1 = ((Ring) a).getValue(tab[0], level);
//        }else {
//            i1 = get(a, tab, 0, level);
//        }
//        if (tab[1].matches("[0-9]+")){
//            i2 = Double.parseDouble(tab[1]);
//        }else{
//            i2 = get(a, tab, 1, level);
//        }
//
//        return calc(i1, i2, sym);
//    }

//    public static long get(WavenEntity a, String[] tab, int index, int level){
//        long i1 = -0;
//        for (Object o : a.getDynamicValueReferences()){
//            JSONObject jsonObject = (JSONObject) o;
//            if (jsonObject.get(REF_ID).equals(tab[index]) && jsonObject.containsKey(VALUE)){
//                i1 = ((Long) jsonObject.get(VALUE));
//            }else if (jsonObject.get(REF_ID).equals(tab[index])){
//                i1 = WavenMath.getNumber(jsonObject, level, a, tab[index]);
//            }
//        }
//
//        return i1;
//    }

    public static String findSymbole(String str) {
        if (str.contains("/")) {
            return "/";
        } else if (str.contains("+")) {
            return "\\+";
        } else if (str.contains("-")) {
            return "-";
        } else if (str.contains("*")) {
            return "*";
        } else {
            System.err.println("erreur symbole WavenMath findSymbole : " + str);
            return "";
        }
    }
}
