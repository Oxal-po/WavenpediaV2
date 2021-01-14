package fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

public interface PrecomputedEntity extends WavenInterface {


    String PRECOMPUTE_DATA = "precomputedData";

    JsonObject getPrecomputeData();

    default JsonObject getPrecomputeData(JsonObject j){
        return j.get(PRECOMPUTE_DATA).getAsJsonObject();
    }
}
