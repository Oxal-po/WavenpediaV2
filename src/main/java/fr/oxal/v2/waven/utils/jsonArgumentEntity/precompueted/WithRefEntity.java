package fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.ref.RefUtils;

import java.util.Optional;

public interface WithRefEntity extends PrecomputedEntity {

    String KEY_WORD_REF = "keywordReferences";

    int SUMMONING_ID_TYPE = 4;
    int MECHA_ID_TYPE = 7;
    int FLOOR_MECHA_ID_TYPE = 6;
    int ASTRE_ID_TYPE = 52;
    int COMPA_ID_TYPE = 5;
    int SPELL_ID_TYPE = 25;
    int RING_ID_TYPE = 32;

    String TYPE = "type";
    String ID = "id";


    default Optional<JsonArray> getWavenRef() {
        if (getPrecomputeData().has(KEY_WORD_REF)) {
            return Optional.of(getPrecomputeData().get(KEY_WORD_REF).getAsJsonArray());
        }

        return Optional.empty();
    }


    default Optional<WavenEntity> getEntityByRef(JsonObject j) {
        return RefUtils.getEntityByRef(j, TYPE, ID);
    }
}
