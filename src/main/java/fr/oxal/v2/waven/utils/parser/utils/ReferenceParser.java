package fr.oxal.v2.waven.utils.parser.utils;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface ReferenceParser<Z> extends WavenObjectParser<Z>{

    String getKeyRef(String text);

    default Optional<JsonObject> getJsonRef(String text, WavenInterface entity){
        return entity.asDynamicedEntity().getDynamicJson(getKeyRef(text));
    }

    default Optional<Integer> getValue(String text, int level, WavenInterface entity){
        return entity.asDynamicedEntity().getDynamicValue(getKeyRef(text), level);
    }
}
