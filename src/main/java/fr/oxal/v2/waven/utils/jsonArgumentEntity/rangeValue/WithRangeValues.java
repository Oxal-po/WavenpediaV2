package fr.oxal.v2.waven.utils.jsonArgumentEntity.rangeValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface WithRangeValues extends WavenInterface {

    String RANGE_VALUE = "rangeValues";
    String VALUE = "value";
    String REF_NAME = "thisRingValueRefName";
    String MIN = "min";
    String MAX = "max";
    String LEVEL_AVAILABLE = "visibleLevelAvailable";

    Optional<JsonObject> getRangeValues();
    default Optional<JsonObject> getRangeValues(JsonObject o){
        return Optional.of((JsonObject) o.get(RANGE_VALUE));
    }

    default Optional<JsonArray> getArrayValues(){
        return getRangeValues().map(a -> (JsonArray) a.get(VALUE));
    }

    default Optional<JsonObject> getArrayValue(String name){
        return getArrayValues().map(a -> {
            for (JsonElement e : a){
                if (name.equals(e.getAsJsonObject().get(REF_NAME).getAsString())){
                    return e.getAsJsonObject();
                }
            }
            return null;
        });
    }

    default Optional<Integer> getMinLevel(){
        return getRangeValues().map(a -> a.get(MIN).getAsInt());
    }

    default Optional<Integer> getMaxLevel(){
        return getRangeValues().map(a -> a.get(MAX).getAsInt());
    }

    default Optional<JsonArray> getAvailableLevel(){
        return getRangeValues().map(a -> a.get(LEVEL_AVAILABLE).getAsJsonArray());
    }

    default Optional<Integer> getArrayValue(String name, int level){
        return getArrayValue(name).map(a -> WavenMath.getNumber(a.get(VALUE).getAsJsonObject(), level, this));
    }
}
