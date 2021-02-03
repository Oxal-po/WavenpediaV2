package fr.oxal.v2.waven.entity.base.StatEntity.floorMechanism;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.FamiliesEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;

import java.util.Optional;

public class FloorMechanism extends NamedWavenEntity implements DynamicedEntity, WithEffect, FamiliesEntity {

    public final static String PATH_FLOOR_MECA = Wavenpedia.jsonPath + "FloorMechanismDefinition/";
    public final static String FLOOR_TYPE = "floorType";

    public FloorMechanism(int id) {
        super(id);
    }


    public Optional<Integer> getFloorType(){
        JsonPrimitive primi = (JsonPrimitive) getJsonRepresentation().get(FLOOR_TYPE);

        if (primi != null){
            return Optional.of(primi.getAsInt());
        }
        return Optional.empty();
    }

    @Override
    public String getPathFolder() {
        return PATH_FLOOR_MECA;
    }

    @Override
    public String getNameDictionnaire() {
        return MECHANISM;
    }

    @Override
    public Optional<JsonArray> getFamilies() {
        return getFamilies(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getDetails() {
        return Optional.empty();
    }
}
