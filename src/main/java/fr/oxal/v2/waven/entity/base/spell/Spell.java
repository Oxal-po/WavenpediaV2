package fr.oxal.v2.waven.entity.base.spell;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.StatEntity.WithStatEntities;
import fr.oxal.v2.waven.entity.base.god.WithGods;
import fr.oxal.v2.waven.entity.pvm.drop.SingleObtainableItemList;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithFilters;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.EquipeableEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.WithElement;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.WithImage;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.Dropeable;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.MerchantCost;
import fr.oxal.v2.waven.utils.updateGauge.WithCost;
import fr.oxal.v2.waven.utils.updateGauge.WithGains;

import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

public class Spell extends NamedWavenEntity implements WithGods, EquipeableEntity, WithSpells, WithEffect,
        DynamicedEntity, WithFilters, WithElement, WithImage, WithGains, WithCost, WithStatEntities, MerchantCost,
        Dropeable {

    public static final String PATH_SPELL = Wavenpedia.jsonPath + "SpellDefinition/";

    public Spell(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_SPELL;
    }

    @Override
    public String getNameDictionnaire() {
        return SPELL;
    }

    @Override
    public void clone(WavenEntity entity) {
        super.clone(entity);
    }

    @Override
    public Optional<JsonArray> getFamilies() {
        return getFamilies(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getDetails() {
        return getDetails(getJsonRepresentation());
    }

    @Override
    public boolean isEquipeable() {
        return isEquipeable(getJsonRepresentation());
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
    public JsonObject getPrecomputeData() {
        return getPrecomputeData(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getCastTarget() {
        return getCastTarget(getJsonRepresentation());
    }

    @Override
    public Optional<FileInputStream> getImage() {
        return getImage(this);
    }

    @Override
    public Optional<JsonArray> getArrayCosts() {
        return getArrayCosts(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getArrayGains() {
        return getArrayGains(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getMerchantCost() {
        return getMerchantCost(getJsonRepresentation());
    }

    @Override
    public List<SingleObtainableItemList> getDropZone() {
        return getDropZone(this);
    }
}
