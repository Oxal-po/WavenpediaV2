package fr.oxal.v2.waven.utils.jsonArgumentEntity.ref;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.base.Companion;
import fr.oxal.v2.waven.entity.base.StatEntity.floorMechanism.FloorMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.mechanism.ObjectMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.summoning.Summoning;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.pvm.equipment.ring.Ring;
import fr.oxal.v2.waven.entity.pvm.resource.astre.RingResourceDefinition;
import fr.oxal.v2.waven.utils.collections.WavenEntities;

import java.util.Optional;

public class RefUtils {

    public static final int SUMMONING_ID_TYPE = 4;
    public static final int MECHA_ID_TYPE = 7;
    public static final int FLOOR_MECHA_ID_TYPE = 6;
    public static final int ASTRE_ID_TYPE = 52;
    public static final int COMPA_ID_TYPE = 5;
    public static final int SPELL_ID_TYPE = 25;
    public static final int RING_ID_TYPE = 32;
    public static final int inconu = 23;

    public static Optional<WavenEntity> getEntityByRef(JsonObject j, String type, String value) {
        if (j.has(type) && j.has(value)) {
            Class c = null;
            switch (j.get(type).getAsInt()) {
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
                case RING_ID_TYPE:
                    c = Ring.class;
                    break;
                case ASTRE_ID_TYPE:
                    c = RingResourceDefinition.class;
                    break;
                default:
                    //System.err.println("error type id nom reconnu : " + j.get(type).getAsInt());
            }
            if (c != null) {
                return WavenEntities.construct(c, j.get(value).getAsInt());
            }
        }

        return Optional.empty();
    }
}
