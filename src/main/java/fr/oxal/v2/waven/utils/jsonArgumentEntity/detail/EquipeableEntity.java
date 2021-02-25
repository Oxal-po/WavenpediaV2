package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonObject;

public interface EquipeableEntity extends DetailsEntity {


    String EQUIPABLE = "equipableByPlayers";
    String EQUIPABLE_DETAIL = "equipable";

    boolean isEquipeable();

    default boolean isEquipeable(JsonObject j){
        if (j.has(EQUIPABLE) && j.get(EQUIPABLE).isJsonPrimitive()) {
            return j.get(EQUIPABLE).getAsBoolean();
        } else if (getDetails().isPresent() && getDetails().get().has(EQUIPABLE_DETAIL) && getDetails().get().get(EQUIPABLE_DETAIL).isJsonPrimitive()) {
            return getDetails().get().get(EQUIPABLE_DETAIL).getAsBoolean();
        } else {
            return false;
        }
    }
}
