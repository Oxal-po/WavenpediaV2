package fr.oxal.v2.utils.math;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.entity.pvm.equipment.ring.Ring;

import java.io.*;
import java.util.Optional;

public class WavenMath {

    private static final String TYPE = "type";
    private static final String VALUE = "value";

    private static final String REF_ID = "referenceId";
    private static final String REF_NAME = "valueRefName";
    private static final String FUNCTION_LINEAR = "LinearLevelOnlyBasedDynamicValue";
    private static final String FUNCTION_MULT_SCALING = "MultiplyScaleLevelOnlyBasedDynamicValue";
    private static final String FUNCTION_LIN_SCALING = "LinearScaleLevelOnlyBasedDynamicValue";
    private static final String FUNCTION_SUM = "SumValue";
    private static final String SPELL_EVOLVE = "ThisSpellEvolutionValue";
    private static final String FUNCTION_RANGE_VALUE_RING = "ThisRingRangeDynamicValue";
    private static final String FACTOR = "factor";
    private static final String BASE_VALUE = "baseValue";
    public static final String JSON = "m_jsonRepresentation";
    private static final String FIGHT = "fightConstants";
    private static final String COMPA_SCALE = "companionStatScalingFactor";
    private static final String SPELL_MONSTER_SCALING = "monsterSpellScalingFactor";
    private static final String INVOC_SCALE = "summoningStatScalingFactor";
    private static final String MONSTER_SCALE = "monsterStatScalingFactor";

    //todo surement a refaire

    public static int getNumber(JsonObject j, int level, WavenInterface w){
        if (j == null) return -1000;
        if(j.get(TYPE).getAsString().equals(FUNCTION_LINEAR)){
            return linear(j, level);
        }else if(j.get(TYPE).getAsString().equals(FUNCTION_MULT_SCALING)){
            return scalingMult(j, level);
        }else if(j.get(TYPE).getAsString().equals(FUNCTION_LIN_SCALING)){
            return scaling(j, level);
        }else if(j.get(TYPE).getAsString().equals(FUNCTION_RANGE_VALUE_RING)){
            return ringRange(level, w, j.get(REF_NAME).getAsString());
        }else if(j.get(TYPE).getAsString().equals(FUNCTION_SUM)){
            return sum(j);
        }else if(j.get(TYPE).getAsString().equals("ConstIntegerValue")){
            return j.get(VALUE).getAsInt();
        }else if(j.get(TYPE).getAsString().equals("SpellsCountValue")){
            return -1000;
        }else if(j.get(TYPE).getAsString().equals("NegativeValue")){
            return getNumber((JsonObject) j.get("valueToNeg"), level, w);
        }else if(j.get(TYPE).getAsString().equals(SPELL_EVOLVE)){
            return 0;
        }else{
            System.err.println("erreur wavenMath getNumber");
            //j.forEach((k, v) -> System.out.println(k + " : " + v));
            System.err.println(j.get(TYPE));
        }
        return -1000;
    }

    private static int sum(JsonObject j) {
        int base = 0;
        JsonArray jsonArray = (JsonArray) j.get("valuesToSum");
        for (Object object : jsonArray){
            JsonObject json = (JsonObject) object;
            if (json.has(TYPE)){
                if (json.has(VALUE)){
                    base = json.get(VALUE).getAsInt();
                }
            }
        }

        return base;
    }

    private static int ringRange(int level, WavenInterface a, String key) {
        Ring r =  a.asRing();
        Optional<Integer> i = r.getArrayValue(key, level);
        if (i.isPresent()){
            return i.get();
        }
        System.err.println("-ERROR- WavenMath : ringRange : " + r.getId() + " : " + i + " : " + key);
        return -1000;
    }

    public static int linear(JsonObject j, int level){
        double base = j.get(BASE_VALUE).getAsDouble();
        double factor = j.get(FACTOR).getAsDouble();
        return (int) Math.round((base + level*factor));
    }

    public static int scaling(JsonObject j, int level){
        double base = j.get(BASE_VALUE).getAsDouble();
        return (int) Math.round((base + (base * parserConst(j) * (level - 1))));
    }

    public static long getExp(int level){
        double xp = 400;
        for (int i = 1; i < level; i++){
            xp *= 1.1;
        }

        return Math.round(xp);
    }

    public static int scalingMult(JsonObject j, int level){
        double base = j.get(BASE_VALUE).getAsDouble();
        return (int) Math.round(base * Math.pow(parserConst(j) + 1, level - 1));
    }

    public static double parserConst(JsonObject j){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Wavenpedia.constPath))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonObject factorJson = (JsonObject) j.get("factor");
        String name = "";

        if (factorJson.get("reference") instanceof JsonPrimitive){
            JsonPrimitive p = factorJson.get("reference").getAsJsonPrimitive();
            switch (p.getAsInt()){
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


            if (jsonObject.has(VALUE)){
                return jsonObject.get(VALUE).getAsJsonPrimitive().getAsInt();
            }else{
                return jsonObject.get(name).getAsJsonPrimitive().getAsDouble();
            }
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return 0;
    }

    public static double calc(double i1, double i2, String symbole){
        switch (symbole){
            case "/":
                return i1/i2;
            case "+":
            case "\\+":
                return i1+i2;
            case "-":
                return i1-i2;
            case "*":
            case "\\*":
                return i1*i2;
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

    public static String findSymbole(String str){
        if (str.contains("/")){
            return "/";
        }else if (str.contains("+")){
            return "\\+";
        }else if (str.contains("-")){
            return "-";
        }else if (str.contains("*")){
            return "*";
        }else {
            System.err.println("erreur symbole WavenMath findSymbole : " + str);
            return "";
        }
    }
}
