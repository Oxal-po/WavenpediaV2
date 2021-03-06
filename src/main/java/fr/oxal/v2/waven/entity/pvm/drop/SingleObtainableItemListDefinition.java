package fr.oxal.v2.waven.entity.pvm.drop;

import com.google.gson.JsonArray;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.WithItems;

import java.util.Optional;

public class SingleObtainableItemListDefinition extends WavenEntity implements WithItems {

    public static final String PATH = Wavenpedia.jsonPath + "SingleObtainableItemListDefinition/";

    public SingleObtainableItemListDefinition(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH;
    }

    @Override
    public Optional<JsonArray> getItemsArray() {
        return getItemsArray(getJsonRepresentation());
    }

    @Override
    public int compareTo(WavenInterface wavenEntity) {
        return 0;
    }
}
