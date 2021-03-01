package fr.oxal.v2.waven.utils.jsonArgumentEntity.rangeValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.oxal.v2.utils.math.WavenMath.FACTOR;

public interface WithRangeValues extends WavenInterface {

    String RANGE_VALUE = "rangeValues";
    String VALUE = "value";
    String REF_NAME = "thisRingValueRefName";
    String MIN = "min";
    String MAX = "max";
    String LEVEL_AVAILABLE = "visibleLevelAvailable";

    Optional<JsonObject> getRangeValues();

    default Optional<JsonObject> getRangeValues(JsonObject o) {
        if (o.has(RANGE_VALUE)) {
            return Optional.of((JsonObject) o.get(RANGE_VALUE));
        }
        return Optional.empty();
    }

    default Optional<JsonArray> getArrayValues() {
        return getRangeValues().map(a -> (JsonArray) a.get(VALUE));
    }

    default Optional<JsonObject> getArrayValue(String name) {
        return getArrayValues().map(a -> {
            for (JsonElement e : a) {
                if (name.equals(e.getAsJsonObject().get(REF_NAME).getAsString())) {
                    return e.getAsJsonObject();
                }
            }
            return null;
        });
    }

    default Optional<Double> getFactorRange(String name) {
        return getArrayValues().map(a -> {
            for (JsonElement e : a) {
                if (name.equals(e.getAsJsonObject().get(REF_NAME).getAsString())) {
                    return e.getAsJsonObject().get(FACTOR).getAsDouble();
                }
            }
            return 0.0;
        });
    }

    default Optional<Integer> getMinRandom() {
        return getRangeValues().map(a -> a.get(MIN).getAsInt());
    }

    default Optional<Integer> getMaxRandom() {
        return getRangeValues().map(a -> a.get(MAX).getAsInt());
    }

    default Optional<JsonArray> getAvailableLevel() {
        return getRangeValues().map(a -> a.get(LEVEL_AVAILABLE).getAsJsonArray());
    }

    default List<Integer> getLevels() {
        ArrayList<Integer> list = new ArrayList<>();

        getAvailableLevel().ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonPrimitive() && e.getAsJsonPrimitive().isNumber()) {
                    list.add(e.getAsInt());
                }
            }
        });

        return list;
    }

    default int getMaxLevel() {
        return getLevels().stream().max(Integer::compareTo).orElse(0);
    }

    default int getMinLevel() {
        return getLevels().stream().min(Integer::compareTo).orElse(0);
    }

    default Optional<Integer> getArrayValue(String name, int level) {
        return getArrayValue(name).map(a -> WavenMath.getNumber(a.get(VALUE).getAsJsonObject(), level, this));
    }
}
