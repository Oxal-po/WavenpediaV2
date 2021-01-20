package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface DetailsEntity extends WavenInterface {

    String DETAILS = "details";

    default Optional<JsonObject> getDetails(JsonObject j){
        return Optional.of((JsonObject) j.get(DETAILS));
    }

    Optional<JsonObject> getDetails();
}
