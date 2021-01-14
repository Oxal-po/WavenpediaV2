package fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget;

import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithSelector extends WithCastTarget {

    String SELECTOR = "selector";

    default Optional<JsonObject> getSelector(){
        Optional<JsonObject> o = getCastTarget();
        if (o.isPresent()){
            return Optional.of((JsonObject) o.get().get(SELECTOR));
        }
        return Optional.empty();
    }
}
