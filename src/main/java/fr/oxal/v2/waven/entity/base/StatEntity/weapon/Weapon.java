package fr.oxal.v2.waven.entity.base.StatEntity.weapon;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.base.StatEntity.StatEntity;
import fr.oxal.v2.waven.entity.base.god.WithGods;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.skill.WithSkills;
import fr.oxal.v2.waven.entity.pvm.skill.WithSpecificSkills;
import fr.oxal.v2.waven.utils.stat.WithAtk;
import fr.oxal.v2.waven.utils.stat.WithLife;
import fr.oxal.v2.waven.utils.stat.WithPm;

import java.util.List;
import java.util.Optional;

public class Weapon extends StatEntity implements WithAtk, WithPm, WithLife,
        WithGods, WithSpells, WithSpecificSkills {

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
    public JsonArray getFamilies() {
        return getFamilies(getJsonRepresentation());
    }

    @Override
    public JsonObject getDetails() {
        return getDetails(getJsonRepresentation());
    }

    @Override
    public JsonArray getFamilies(JsonObject j) {
        return j.get(FAMILIES).getAsJsonArray();
    }

    @Override
    public List<Spell> getSpells() {
        return getSpells(getJsonRepresentation());
    }
    @Override
    public List<Double> getIdSpells() {
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
}
