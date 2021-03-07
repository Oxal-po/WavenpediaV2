package fr.oxal.v2.waven.entity.pvm.equipment.ring;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.pvm.drop.SingleObtainableItemList;
import fr.oxal.v2.waven.entity.pvm.equipment.Equipment;
import fr.oxal.v2.waven.entity.pvm.skill.WithUnlockableSkill;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.Dropeable;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.rangeValue.WithRangeValues;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.rarity.WithRarity;

import java.util.List;
import java.util.Optional;

public class RingEquipment extends Equipment implements WithEffect, DynamicedEntity, WithRangeValues
        , WithPassiveModifiers, WithRarity, Dropeable, WithUnlockableSkill {

    public static final String PATH_RING = Wavenpedia.jsonPath + "RingEquipmentDefinition/";

    public RingEquipment(int id) {
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
    public Optional<JsonArray> getPassiveModifiers() {
        return getPassiveModifiers(getJsonRepresentation());
    }

    @Override
    public Optional<Integer> getRarity() {
        return getRarity(getJsonRepresentation());
    }

    @Override
    public List<SingleObtainableItemList> getDropZone() {
        return getDropZone(this);
    }

    @Override
    public Optional<JsonArray> getJsonArraySkills() {
        return getJsonArraySkills(getJsonRepresentation());
    }
}
