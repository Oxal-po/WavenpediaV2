package fr.oxal.v2.waven.entity.base.StatEntity.summoning;

import com.google.gson.JsonArray;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.utils.stat.WithAtk;
import fr.oxal.v2.waven.utils.stat.WithLife;
import fr.oxal.v2.waven.utils.stat.WithPm;

import java.util.List;
import java.util.Optional;

public class Summoning extends NamedWavenEntity implements WithEffect, WithPm, WithAtk, WithLife, WithSpells {

    public final static String PATH_SUMMONING = Wavenpedia.jsonPath + "SummoningDefinition/";
    public final static String MONSTRE = "MONSTRE";
    private static final int MAX_LEVEL_SUMMONING = 100;

    public Summoning(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_SUMMONING;
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
    public String getNameDictionnaire() {
        return SUMMONING;
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

    public boolean isMonster() {
        return getDisplayName().contains(MONSTRE);
    }

    @Override
    public Optional<JsonArray> getStatsArray() {
        return getStatsArray(getJsonRepresentation());
    }
}
