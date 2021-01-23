package fr.oxal.v2.waven.entity.pvm.skill;

import com.google.gson.JsonArray;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;

import java.util.Optional;

public class Skill extends NamedWavenEntity implements WithEffect, DynamicedEntity, WithPassiveModifiers {

    public final static String PATH_SKILL = Wavenpedia.jsonPath + "SkillDefinition/";

    public Skill(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_SKILL;
    }

    @Override
    public String getNameDictionnaire() {
        return SKILLS;
    }

    @Override
    public Optional<JsonArray> getPassiveModifiers() {
        return getPassiveModifiers(getJsonRepresentation());
    }
}
