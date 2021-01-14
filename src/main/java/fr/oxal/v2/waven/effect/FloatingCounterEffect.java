package fr.oxal.v2.waven.effect;

import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.PrecomputedEntity;

public class FloatingCounterEffect extends WavenEntity implements WithEffect, DynamicedEntity {

    public final static String PATH_COUNTER_EFFECT = Wavenpedia.jsonPath + "FloatingCounterCustomEffectDefinition/";

    public FloatingCounterEffect(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_COUNTER_EFFECT;
    }

    @Override
    public int compareTo(WavenEntity wavenEntity) {
        return 0;
    }

    @Override
    public JsonObject getPrecomputeData() {
        return getPrecomputeData(getJsonRepresentation());
    }
}
