package fr.oxal.v2.waven.utils.jsonArgumentEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface WithSlot extends WavenInterface {

    String SLOTS = "slots";
    String NB_SLOT = "v";
    String LEVEL = "k";

    Optional<JsonArray> getDetailSlots();

    default Optional<JsonArray> getDetailSlots(JsonObject j){
        return Optional.of((JsonArray) j.get(SLOTS));
    }

    default Optional<Integer> getSlotByLevel(int level){
        return getDetailSlots().map(a -> {
            int slot = 0;
            for (JsonElement e : a){
                if (e.getAsJsonObject().get(LEVEL).getAsInt() < level){
                    slot = e.getAsJsonObject().get(NB_SLOT).getAsInt();
                }else{
                    return slot;
                }
            }
            return slot;
        });
    }
}
