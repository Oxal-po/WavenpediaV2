package fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface DynamicedEntity extends PrecomputedEntity {

    String DYNAMIC_VALUE = "dynamicValueReferences";
    String TYPE = "type";
    String REF_ID = "referenceId";
    String VALUE = "value";

    default JsonArray getDynamicValues(){
        return getPrecomputeData().get(DYNAMIC_VALUE).getAsJsonArray();
    }

    default Optional<JsonObject> getDynamicJson(String key){
        for (JsonElement e : getDynamicValues()){
            if (e.isJsonObject()){
                if (e.getAsJsonObject().get(REF_ID).getAsString().equals(key)){
                    return Optional.of(e.getAsJsonObject());
                }
            }
        }

        return Optional.empty();
    }

    default Optional<Integer> getDynamicValue(String key, int level){
        Optional<JsonObject> o = getDynamicJson(key);

        if (o.isPresent()){
            return Optional.of(WavenMath.getNumber(o.get(), level));
        }

        return Optional.empty();
    }
}
