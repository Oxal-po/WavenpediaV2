package fr.oxal.v2.waven.utils.updateGauge;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.element.WavenElement;

import java.util.List;
import java.util.Optional;

public interface WithGains extends UpdateGauge {

    String GAINS = "gaugeToModifyOnSpellPlay";

    Optional<JsonArray> getArrayGains();

    default Optional<JsonArray> getArrayGains(JsonObject j){
        return Optional.of(j.get(GAINS).getAsJsonArray());
    }

    default List<WavenElement> getGains(){
        return getElements(getArrayGains());
    }
}
