package fr.oxal.v2.waven.utils.jsonArgumentEntity.passif;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

import static fr.oxal.v2.utils.math.WavenMath.REF_NAME;

public interface WithPassiveModifiers extends DynamicedEntity {

    String PASSIVE_MODIFIERS = "passiveModifiers";
    String MODIFIER = "modifier";

    Optional<JsonArray> getPassiveModifiers();

    default Optional<JsonArray> getPassiveModifiers(JsonObject o) {
        return Optional.of((JsonArray) o.get(PASSIVE_MODIFIERS));
    }

    default ArrayList<Modifier> getModifiers() {
        ArrayList<Modifier> modifs = new ArrayList<>();

        getPassiveModifiers().ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonObject()) {
                    modifs.add(new Modifier(e.getAsJsonObject(), this));
                }
            }
        });

        return modifs;
    }

    default Optional<Modifier> getModifier(String key) {
        for (Modifier m : getModifiers()) {
            if (m.getRefId().equals(key)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }


    class Modifier {
        public static final String CARACT_IDS = "caracIds";

        private ArrayList<Integer> caracId;
        private String type, refId, refName;
        private Function<Integer, Integer> function;

        public Modifier() {
            caracId = new ArrayList<>();
        }

        public Modifier(JsonObject j, WavenInterface w) {
            this();
            JsonObject json = j.get(MODIFIER).getAsJsonObject();
            for (JsonElement e : json.get(CARACT_IDS).getAsJsonArray()) {
                if (e.isJsonPrimitive()) {
                    caracId.add(e.getAsInt());
                } else {
                    System.err.println(e);
                }
            }
            type = json.get(TYPE).getAsString();
            setup(j.get(VALUE).getAsJsonObject(), w);
        }

        private void setup(JsonObject j, WavenInterface w) {
            refId = j.get(REF_ID).getAsString();
            refName = j.get(REF_NAME).getAsString();

            Optional<JsonObject> json = w.asDynamicedEntity().getDynamicJson(getRefId());
            if (json.isPresent()) {
                function = i -> WavenMath.getNumber(json.get(), i, w);
            }
        }

        public int getValue(int level) {
            return function.apply(level);
        }

        public ArrayList<Integer> getCaracId() {
            return caracId;
        }

        public void setCaracId(ArrayList<Integer> caracId) {
            this.caracId = caracId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRefId() {
            return refId;
        }

        public void setRefId(String refId) {
            this.refId = refId;
        }

        public String getRefName() {
            return refName;
        }

        public void setRefName(String refName) {
            this.refName = refName;
        }

        @Override
        public String toString() {
            return "Modifier{" +
                    "caracId=" + caracId +
                    ", type='" + type + '\'' +
                    ", refId='" + refId + '\'' +
                    ", refName='" + refName + '\'' +
                    '}';
        }
    }
}
