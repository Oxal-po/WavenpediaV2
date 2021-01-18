package fr.oxal.v2.waven.entity.pvm.skill;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithSpecificSkills extends WithSkills {

    String SPE_SKILLS = "specificSkills";

    Optional<JsonArray> getJsonArraySpecificSkills();

    default Optional<JsonArray> getJsonArraySpecificSkills(JsonObject o) {
        return Optional.of((JsonArray) o.get(SPE_SKILLS));
    }

    @Override
    default Optional<JsonArray> getJsonArraySkills(){
        JsonArray array = new JsonArray();
        getJsonArraySpecificSkills().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonObject() && e.getAsJsonObject().has(SKILLS) && e.getAsJsonObject().get(SKILLS).isJsonArray()){
                    for (JsonElement el : e.getAsJsonObject().get(SKILLS).getAsJsonArray()){
                        if (el.isJsonPrimitive()){
                            array.add(el);
                        }
                    }
                }
            }
        });
        return Optional.of(array);
    }
}
