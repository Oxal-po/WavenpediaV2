package fr.oxal.v2.waven.entity.base;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.skill.WithSkills;
import fr.oxal.v2.waven.entity.pvm.skill.WithUnlockableSkill;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.EquipeableEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.skin.WithSkin;
import fr.oxal.v2.waven.utils.jsonCreator.Jsoneable;
import fr.oxal.v2.waven.utils.stat.WithAtk;
import fr.oxal.v2.waven.utils.stat.WithCritical;
import fr.oxal.v2.waven.utils.stat.WithLife;
import fr.oxal.v2.waven.utils.stat.WithPm;
import fr.oxal.v2.waven.utils.updateGauge.WithCost;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Companion extends NamedWavenEntity implements WithUnlockableSkill, WithSkin, WithLife, WithPm,
        WithAtk, WithCost, Jsoneable, WithSpells, WithCritical, EquipeableEntity {

    private final static String PATH_COMPANION = Wavenpedia.jsonPath + "CompanionDefinition/";

    public Companion(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_COMPANION;
    }

    @Override
    public String getNameDictionnaire() {
        return COMPANION;
    }

    @Override
    public Optional<JsonArray> getJsonArraySkills() {
        return getJsonArraySkills(getJsonRepresentation());
    }

    @Override
    public Optional<Integer> getDefaultSkin() {
        return getDefaultSkin(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getSkins() {
        return getSkins(getJsonRepresentation());
    }

    @Override
    public ArrayList<FileInputStream> getFileSkins() {
        return getFileSkins(this);
    }

    @Override
    public Optional<Double> getAtk(int level) {
        return getAtk(level, getJsonRepresentation());
    }

    @Override
    public Optional<Double> getLife(int level) {
        return getLife(level, getJsonRepresentation());
    }

    @Override
    public Optional<Double> getPm(int level) {
        return getPm(level, getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getArrayCosts() {
        return getArrayCosts(getJsonRepresentation());
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
    public JsonObject transformToJson() {
        JsonObject json = defaultJson(this);
        json.add(NAME_JSON, new JsonPrimitive(getGlobalParsedName(1)));
        String descri = getGlobalParsedDescription(1);
        if (!descri.equals(NamedEntity.BASE_STRING)){
            json.add(DESCRI_JSON, new JsonPrimitive(descri));
        }
        json.add("spells", Jsoneable.toJsonArray(getIdSpells()));
        json.add(STAT_JSON, Jsoneable.statJson(this));
        json.add("costs", Jsoneable.costJson(this));

        return json;
    }

    @Override
    public Optional<Double> getCriticalDamage(int level) {
        return getCriticalDamage(level, getJsonRepresentation());
    }

    @Override
    public Optional<Double> getCriticalChance(int level) {
        return getCriticalChance(level, getJsonRepresentation());
    }

    @Override
    public boolean isEquipeable() {
        return isEquipeable(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getDetails() {
        return Optional.empty();
    }
}
