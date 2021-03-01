package fr.oxal.v2.waven.entity.pvm.item;

import com.google.gson.JsonObject;

import java.util.Optional;

public class GenericItem implements Item {

    private final JsonObject json;

    public GenericItem(JsonObject json) {
        this.json = json;
    }

    @Override
    public String getType() {
        return getType(json);
    }

    @Override
    public double getDropChance() {
        return getDropChance(json);
    }

    @Override
    public Optional<JsonObject> getItem() {
        return getItem(json);
    }
}
