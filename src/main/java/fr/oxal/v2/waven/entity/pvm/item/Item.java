package fr.oxal.v2.waven.entity.pvm.item;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.ref.RefUtils;

import java.util.Optional;

public interface Item extends WavenInterface {

    String LOOT = "OwnableItemInfo";
    String GENERIC = "GenericOwnableInfo";
    String DROP_CHANCE = "dropChance";
    String TYPE = "type";
    String ITEM = "item";
    String COUNT = "count";
    String ITEM_ID = "ownableId";
    String TYPE_ENUM = "typeEnum";
    String VALUE = "value";

    static Optional<Item> getItemByJson(JsonObject json) {
        if (json.has(TYPE)) {
            if (json.has(ITEM)) {
                if (json.get(ITEM).getAsJsonObject().get(TYPE).getAsString().equals(LOOT)) {
                    return Optional.of(new WavenItem(json));
                } else if (json.get(ITEM).getAsJsonObject().get(TYPE).getAsString().equals(GENERIC)) {
                    return Optional.of(new GenericItem(json));
                }
            }
        }

        return Optional.empty();
    }

    String getType();

    double getDropChance();

    Optional<JsonObject> getItem();

    default Optional<JsonObject> getItem(JsonObject json) {
        if (json.has(ITEM)) {
            return Optional.of(json.get(ITEM).getAsJsonObject());
        }
        return Optional.empty();
    }

    default double getDropChance(JsonObject json) {
        if (json.has(DROP_CHANCE)) {
            return json.get(DROP_CHANCE).getAsDouble();
        }
        return 0.0;
    }

    default String getType(JsonObject json) {
        if (json.has(TYPE)) {
            return json.get(TYPE).getAsString();
        }
        return TYPE;
    }

    default Optional<JsonObject> getCount() {
        return getItem().map(json -> {
            if (json.has(COUNT)) {
                return Optional.of(json.get(COUNT).getAsJsonObject());
            }
            return Optional.<JsonObject>empty();
        }).orElse(Optional.empty());
    }

    default Optional<JsonObject> getItemId() {
        return getItem().map(json -> {
            if (json.has(ITEM_ID)) {
                return Optional.of(json.get(ITEM_ID).getAsJsonObject());
            }
            return Optional.<JsonObject>empty();
        }).orElse(Optional.empty());
    }

    default int getMin() {
        return getCount().map(a -> a.get("min").getAsInt()).orElse(0);
    }

    default int getMax() {
        return getCount().map(a -> a.get("max").getAsInt()).orElse(0);
    }

    default Optional<WavenEntity> getEntity() {
        return getItemId().map(json -> RefUtils.getEntityByRef(json, TYPE_ENUM, VALUE)).orElse(Optional.empty());
    }
}
