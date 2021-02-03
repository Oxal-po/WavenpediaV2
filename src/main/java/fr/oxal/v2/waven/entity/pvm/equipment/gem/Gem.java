package fr.oxal.v2.waven.entity.pvm.equipment.gem;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.equipment.Equipment;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.WithElement;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.merchant.Buyable;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.rarity.WithRarity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Gem extends Equipment implements WithEffect, WithSpells, WithRarity,
        Buyable, WithPassiveModifiers, WithElement {

    public static final String PATH_GEM = Wavenpedia.jsonPath + "GemEquipmentDefinition/";

    public Gem(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_GEM;
    }

    @Override
    public List<Integer> getIdSpells() {
        return getIdSpells(getJsonRepresentation());
    }

    @Override
    public List<Spell> getSpells() {
        return getSpells(getJsonRepresentation());
    }

    @Override
    public String getParsedDescription(int level, int... option) {
        return super.getParsedDescription(level, option) + " " + getSpells()
                .stream()
                .map(a -> a.getParsedName(level, option))
                .collect(Collectors.joining(", "))
                ;
    }

    @Override
    public Optional<Integer> getRarity() {
        return getRarity(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getArrayMerchantCost() {
        return getArrayMerchantCost(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getPassiveModifiers() {
        return getPassiveModifiers(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getDetails() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getIdElement() {
        return getIdElement(getJsonRepresentation());
    }
}
