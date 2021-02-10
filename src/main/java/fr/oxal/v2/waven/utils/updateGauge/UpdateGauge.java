package fr.oxal.v2.waven.utils.updateGauge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.element.WavenElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UpdateGauge extends WavenInterface {

    String ELEMENT = "element";
    String VALUE = "value";

    default WavenElement getElement(JsonObject j){
        int id = WavenElement.PA;
        if (j.has(ELEMENT)){id = j.get(ELEMENT).getAsInt();
        }
        WavenElement elem = new WavenElement(id);
        elem.setValue(WavenMath.getNumber(j.get(VALUE).getAsJsonObject(), 1, this));
        return elem;
    }

    default List<WavenElement> getElements(Optional<JsonArray> array){
        ArrayList<WavenElement> list = new ArrayList<>();

        array.ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonObject()){
                    list.add(getElement(e.getAsJsonObject()));
                }
            }
        });

        return list;
    }
}
