package fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.Companion;
import fr.oxal.v2.waven.entity.base.StatEntity.floorMechanism.FloorMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.mechanism.ObjectMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.summoning.Summoning;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.PrecomputedEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithRefEntity extends PrecomputedEntity {

    String KEY_WORD_REF = "keywordReferences";

    int SUMMONING_ID_TYPE = 4;
    int MECHA_ID_TYPE = 7;
    int FLOOR_MECHA_ID_TYPE = 6;
    int COMPA_ID_TYPE = 5;
    int SPELL_ID_TYPE = 25;

    String TYPE = "type";
    String ID = "id";


    default Optional<JsonArray> getWavenRef(){
        if (getPrecomputeData().has(KEY_WORD_REF)){
            return Optional.of(getPrecomputeData().get(KEY_WORD_REF).getAsJsonArray());
        }

        return Optional.empty();
    }



    default Optional<NamedWavenEntity> getEntityByRef(JsonObject j){
        if (j.has(TYPE) && j.has(ID)){
            Class c = null;
            switch (j.get(TYPE).getAsInt()){
                case SUMMONING_ID_TYPE:
                    c = Summoning.class;
                    break;
                case MECHA_ID_TYPE:
                    c = ObjectMechanism.class;
                    break;
                case FLOOR_MECHA_ID_TYPE:
                    c = FloorMechanism.class;
                    break;
                case COMPA_ID_TYPE:
                    c = Companion.class;
                    break;
                case SPELL_ID_TYPE:
                    c = Spell.class;
                    break;
                default:
                    System.err.println("error type id nom reconnu : " + j.get(TYPE).getAsInt());
            }
            if (c != null){
                return WavenEntities.construct(c, j.get(ID).getAsInt());
            }
        }

        return Optional.empty();
    }
}
