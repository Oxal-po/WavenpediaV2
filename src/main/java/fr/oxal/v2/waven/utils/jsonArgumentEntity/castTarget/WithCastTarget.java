package fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface WithCastTarget extends WavenInterface {

    String CAST_TARGET = "castTarget";

    Optional<JsonObject> getCastTarget();
    default Optional<JsonObject> getCastTarget(JsonObject j){
        return Optional.of((JsonObject) j.get(CAST_TARGET));
    }
}
