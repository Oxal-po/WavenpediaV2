package fr.oxal.v2.waven.entity.pvm.item;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public class WavenItem implements Item {

    private final JsonObject json;

    public WavenItem(JsonObject json) {
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

    @Override
    public int compareTo(WavenInterface wavenInterface) {
        return 0;
    }
}
