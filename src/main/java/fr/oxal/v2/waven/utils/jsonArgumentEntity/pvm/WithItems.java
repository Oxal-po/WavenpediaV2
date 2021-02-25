package fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.ref.RefUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithItems extends WavenInterface {

    String ITEMS = "items";
    String OWNABLE_ITEM = "OwnableItemInfo";
    String OWNABLE_ID_ITEM = "ownableId";

    String TYPE_ENUM = "typeEnum";
    String VALUE = "value";

    Optional<JsonArray> getItemsArray();

    default Optional<JsonArray> getItemsArray(JsonObject json) {
        if (json.has(ITEMS) && json.get(ITEMS).isJsonArray()) {
            return Optional.of(json.get(ITEMS).getAsJsonArray());
        }
        return Optional.empty();
    }

    default List<? extends WavenEntity> getItems() {
        ArrayList list = new ArrayList();

        getItemsArray().ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonObject() && e.getAsJsonObject().get("type").getAsJsonPrimitive().getAsString().equals(OWNABLE_ITEM) && e.getAsJsonObject().has(OWNABLE_ID_ITEM)) {
                    getEntityByRef(e.getAsJsonObject().get(OWNABLE_ID_ITEM).getAsJsonObject()).ifPresent(b -> {
                        list.add(b);
                    });
                } else {
                    System.err.println(e);
                }
            }
        });

        return list;
    }

    default boolean contain(WavenEntity waven) {
        return getItems().contains(waven);
    }

    default Optional<WavenEntity> getEntityByRef(JsonObject j) {
        return RefUtils.getEntityByRef(j, TYPE_ENUM, VALUE);
    }
}
