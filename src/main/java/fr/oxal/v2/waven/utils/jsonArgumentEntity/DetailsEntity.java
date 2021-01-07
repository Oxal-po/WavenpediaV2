package fr.oxal.v2.waven.utils.jsonArgumentEntity;

import com.google.gson.JsonObject;

public interface DetailsEntity {

    String DETAILS = "details";

    default JsonObject getDetails(JsonObject j){
        return (JsonObject) j.get(DETAILS);
    }
    JsonObject getDetails();
}
