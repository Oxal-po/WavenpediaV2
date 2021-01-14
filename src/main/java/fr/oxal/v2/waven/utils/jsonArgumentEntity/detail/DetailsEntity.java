package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

public interface DetailsEntity extends WavenInterface {

    String DETAILS = "details";

    default JsonObject getDetails(JsonObject j){
        return (JsonObject) j.get(DETAILS);
    }
    JsonObject getDetails();
}
