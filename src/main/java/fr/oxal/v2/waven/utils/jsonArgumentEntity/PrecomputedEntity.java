package fr.oxal.v2.waven.utils.jsonArgumentEntity;

import com.google.gson.JsonObject;

public interface PrecomputedEntity {


    String PRECOMPUTE_DATA = "precomputedData";

    JsonObject getPrecomputeData();

    default JsonObject getPrecomputeData(JsonObject j){
        return j.get(PRECOMPUTE_DATA).getAsJsonObject();
    }
}
