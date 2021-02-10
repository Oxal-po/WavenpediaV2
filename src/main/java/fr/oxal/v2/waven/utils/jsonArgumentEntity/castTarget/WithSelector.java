package fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget;

import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithSelector extends WithCastTarget {

    String SELECTOR = "selector";
    String SELECTOR1 = "selector1";

    default Optional<JsonObject> getSelector(){
        Optional<JsonObject> o = getCastTarget();
        if (o.isPresent()){
            if (o.get().has(SELECTOR)){
                return Optional.of((JsonObject) o.get().get(SELECTOR));
            }else if (o.get().has(SELECTOR1)){
                return Optional.of((JsonObject) o.get().get(SELECTOR1));
            }
        }
        return Optional.empty();
    }
}
