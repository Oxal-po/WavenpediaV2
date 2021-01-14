package fr.oxal.v2.waven.entity.pvm.skill;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;

public class Skill extends NamedWavenEntity implements WithEffect, DynamicedEntity {

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
}
