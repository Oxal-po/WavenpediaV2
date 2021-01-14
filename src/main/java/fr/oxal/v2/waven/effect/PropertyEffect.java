package fr.oxal.v2.waven.effect;

import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.WithRefEntity;

public class PropertyEffect extends WavenEntity implements DynamicedEntity, WithRefEntity {

    public static final String PATH_PROPERTY_EFFECT = Wavenpedia.jsonPath + "PropertyCustomEffectDefinition/";

    public PropertyEffect(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_PROPERTY_EFFECT;
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
