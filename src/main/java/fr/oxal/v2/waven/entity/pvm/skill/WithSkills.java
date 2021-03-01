package fr.oxal.v2.waven.entity.pvm.skill;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface WithSkills extends WavenInterface {

    String SKILLS = "skills";

    Optional<JsonArray> getJsonArraySkills();

    default Optional<JsonArray> getJsonArraySkills(JsonObject o) {
        if (o.has(SKILLS)) {
            return Optional.of((JsonArray) o.get(SKILLS));
        }
        return Optional.empty();
    }

    default List<Skill> getSkills() {
        return getSkills(getJsonArraySkills());
    }

    default List<Skill> getSkills(Optional<JsonArray> array) {
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

    default List<Integer> getIdSkills() {
        return getSkills().stream().map(WavenEntity::getId).collect(Collectors.toList());
    }
}
