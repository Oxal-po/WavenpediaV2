package fr.oxal.v2.waven.entity.base;

import com.google.gson.JsonArray;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.pvm.skill.WithSkills;
import fr.oxal.v2.waven.entity.pvm.skill.WithUnlockableSkill;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.skin.WithSkin;
import fr.oxal.v2.waven.utils.stat.WithAtk;
import fr.oxal.v2.waven.utils.stat.WithLife;
import fr.oxal.v2.waven.utils.stat.WithPm;
import fr.oxal.v2.waven.utils.updateGauge.WithCost;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;

public class Companion extends NamedWavenEntity implements WithUnlockableSkill, WithSkin, WithLife, WithPm,
        WithAtk, WithCost {

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
}
