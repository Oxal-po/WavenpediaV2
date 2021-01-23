package fr.oxal.v2.waven.entity.pvm.skill;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Optional;

public interface WithUnlockableSkill extends WithSkills {

    @Override
    default ArrayList<Skill> getSkills(Optional<JsonArray> array) {
        ArrayList<Skill> skills = new ArrayList<>();
        array.ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonPrimitive() && UnlockableSkill.fileExist(e.getAsInt(), UnlockableSkill.class)) {
                    skills.add(new UnlockableSkill(e.getAsInt()).getSkill());
                }
            }
        });

        return skills;
    }
}
