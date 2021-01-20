package fr.oxal.v2.waven.entity.pvm.skill;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.ArrayList;
import java.util.Optional;

public interface WithSkills extends WavenInterface {

    String SKILLS = "skills";

    Optional<JsonArray> getJsonArraySkills();

    default Optional<JsonArray> getJsonArraySkills(JsonObject o) {
        return Optional.of((JsonArray) o.get(SKILLS));
    }

    default ArrayList<Skill> getSkills() {
        return getSkills(getJsonArraySkills());
    }

    default ArrayList<Skill> getSkills(Optional<JsonArray> array) {
        ArrayList<Skill> skills = new ArrayList<>();
        array.ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonPrimitive() && Skill.fileExist(e.getAsInt(), Skill.class)) {
                    skills.add(new Skill(e.getAsInt()));
                }
            }
        });

        return skills;
    }
}
