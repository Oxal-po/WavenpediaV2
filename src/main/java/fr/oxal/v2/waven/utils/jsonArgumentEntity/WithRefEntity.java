package fr.oxal.v2.waven.utils.jsonArgumentEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithRefEntity extends PrecomputedEntity {

    String KEY_WORD_REF = "keywordReferences";

    default Optional<JsonArray> getWavenRef(){
        if (getPrecomputeData().has(KEY_WORD_REF)){
            return Optional.of(getPrecomputeData().get(KEY_WORD_REF).getAsJsonArray());
        }

        return Optional.empty();
    }
}
