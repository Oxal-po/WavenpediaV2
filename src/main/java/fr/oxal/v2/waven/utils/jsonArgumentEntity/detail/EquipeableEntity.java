package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.DetailsEntity;

public interface EquipeableEntity extends DetailsEntity {


    String EQUIPABLE = "equipableByPlayers";
    String EQUIPABLE_DETAIL = "equipable";

    boolean isEquipeable();

    default boolean isEquipeable(JsonObject j){
        return (j.get(EQUIPABLE) != null && j.get(EQUIPABLE).getAsBoolean()) || getDetails().get(EQUIPABLE_DETAIL).getAsBoolean();
    }
}
