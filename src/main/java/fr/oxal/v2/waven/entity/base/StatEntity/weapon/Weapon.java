package fr.oxal.v2.waven.entity.base.StatEntity.weapon;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.god.WithGods;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.skill.WithSpecificSkills;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.logo.WithLogo;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.skin.WithSkin;
import fr.oxal.v2.waven.utils.jsonCreator.Jsoneable;
import fr.oxal.v2.waven.utils.stat.WithAtk;
import fr.oxal.v2.waven.utils.stat.WithLife;
import fr.oxal.v2.waven.utils.stat.WithPm;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Weapon extends NamedWavenEntity implements WithAtk, WithPm, WithLife,
        WithGods, WithSpells, WithSpecificSkills, WithSkin, WithLogo, Jsoneable {

    public final static String PATH_WEAPON = Wavenpedia.jsonPath + "WeaponDefinition/";

    public Weapon(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_WEAPON;
    }

    @Override
    public String getNameDictionnaire() {
        return WEAPON;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
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
    public Optional<JsonArray> getFamilies() {
        return getFamilies(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getDetails() {
        return getDetails(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getFamilies(JsonObject j) {
        return Optional.of((JsonArray) j.get(FAMILIES));
    }

    @Override
    public List<Spell> getSpells() {
        return getSpells(getJsonRepresentation());
    }
    @Override
    public List<Integer> getIdSpells() {
        return getIdSpells(getJsonRepresentation());
    }

    @Override
    public JsonObject getPrecomputeData() {
        return getPrecomputeData(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getJsonArraySpecificSkills() {
        return getJsonArraySpecificSkills(getJsonRepresentation());
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
    public Optional<FileInputStream> getLogo() {
        return getLogo(this);
    }
}
