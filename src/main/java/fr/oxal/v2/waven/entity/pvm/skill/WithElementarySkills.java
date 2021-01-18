package fr.oxal.v2.waven.entity.pvm.skill;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public interface WithElementarySkills extends WithSkills {

    String ELEMENTARY_SKILL = "weaponElementarySkill";
    String KEY = "k";
    String VALUE = "v";

    Optional<JsonArray> getElementarySkill();

    default Optional<JsonArray> getElementarySkill(JsonObject o){
        return Optional.of((JsonArray) o.get(ELEMENTARY_SKILL));
    }

    @Override
    default Optional<JsonArray> getJsonArraySkills(){
        return getJsonArraySkills(1, 2, 3, 4);
    }

    default ArrayList<Skill> getSkills(int... keys) {
        return getSkills(getJsonArraySkills(keys));
    }

    default Optional<JsonArray> getJsonArraySkills(int... keys){
        JsonArray array = new JsonArray();
        getElementarySkill().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonObject() && e.getAsJsonObject().has(KEY) && e.getAsJsonObject().get(KEY).isJsonPrimitive()){
                    int key = e.getAsJsonObject().get(KEY).getAsInt();
                    if (Arrays.stream(keys).anyMatch(k -> k == key)){
                        if (e.getAsJsonObject().has(VALUE) && e.getAsJsonObject().get(VALUE).isJsonObject()){
                            JsonObject json = e.getAsJsonObject().get(VALUE).getAsJsonObject();
                            if (json.has(SKILLS) && json.get(SKILLS).isJsonArray()){
                                for (JsonElement el : json.get(SKILLS).getAsJsonArray()){
                                    if (el.isJsonPrimitive()){
                                        array.add(el);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        return Optional.of(array);
    }
}
