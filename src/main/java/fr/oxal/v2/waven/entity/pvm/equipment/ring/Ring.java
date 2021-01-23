package fr.oxal.v2.waven.entity.pvm.equipment.ring;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.pvm.equipment.Equipment;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.WithSlot;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.rangeValue.WithRangeValues;

import java.util.Optional;

public class Ring extends Equipment implements WithEffect, DynamicedEntity, WithRangeValues,
        WithSlot, WithPassiveModifiers {

    public static final String PATH_RING = Wavenpedia.jsonPath + "RingEquipmentDefinition/";

    public Ring(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_RING;
    }

    @Override
    public Optional<JsonObject> getRangeValues() {
        return getRangeValues(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getDetailSlots() {
        return getDetailSlots(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getPassiveModifiers() {
        return getPassiveModifiers(getJsonRepresentation());
    }
}
