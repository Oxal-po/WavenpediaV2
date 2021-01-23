package fr.oxal.v2.waven.entity.pvm.skill;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.base.Companion;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.parent.WithParent;

import java.util.Optional;

public class UnlockableSkill extends WavenEntity implements WithParent<Companion> {

    public final static String UNLOCK_SKILL_PAT = Wavenpedia.jsonPath + "SubCompanionUnlockableSkillDefinition/";
    public final static String SKILL = "skill";

    public UnlockableSkill(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return UNLOCK_SKILL_PAT;
    }

    @Override
    public int compareTo(WavenEntity wavenEntity) {
        return 0;
    }

    public Skill getSkill(){
        return new Skill(getJsonRepresentation().get(SKILL).getAsInt());
    }

    @Override
    public int getParentId() {
        return getParentId(getJsonRepresentation());
    }

    @Override
    public Optional<Companion> getParent() {
        return getParent(getClass());
    }
}
