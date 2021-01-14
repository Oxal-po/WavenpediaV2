package fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface WithFilters extends WithSelector{

    String FILTERS = "filters";
    String DYNAMIC = "dynamicValue";

    default Optional<JsonArray> getFilters(){
        return getSelector().map(jsonObject -> (JsonArray) jsonObject.get(FILTERS));
    }

    default Optional<JsonObject> getFilterJson(String key){
        Optional<JsonArray> o = getFilters();
        if (o.isPresent()){
            for (JsonElement e : getFilters().get()){
                if (e.isJsonObject()){
                    if (e.getAsJsonObject().entrySet().stream().anyMatch(entry -> entry.getKey().equals(key))){
                        return Optional.of(e.getAsJsonObject().get(key).getAsJsonObject());
                    }
                }
            }
        }


        return Optional.empty();
    }

    default Optional<Integer> getFilterValue(String key, int level, WavenInterface w){
        Optional<JsonObject> o = getFilterJson(key);

        if (o.isPresent()){
            return Optional.of(WavenMath.getNumber(o.get(), level, w));
        }

        return Optional.empty();
    }

    default Optional<JsonObject> getDynamicFilterJson(JsonObject o){
        return Optional.of((JsonObject) o.get(DYNAMIC));
    }

    default Optional<Integer> getDynamicFilterValue(String key, int level, WavenInterface w){
        Optional<JsonObject> o = getFilterJson(key);
        if (o.isPresent()){
            o = getDynamicFilterJson(o.get());
            if (o.isPresent()){
                return Optional.of(WavenMath.getNumber(o.get(), level, w));
            }
        }

        return Optional.empty();
    }
}
