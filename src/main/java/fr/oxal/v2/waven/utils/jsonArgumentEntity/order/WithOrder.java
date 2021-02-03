package fr.oxal.v2.waven.utils.jsonArgumentEntity.order;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface WithOrder extends WavenInterface {
    String ORDER = "order";

    Optional<Integer> getOrder();

    default Optional<Integer> getOrder(JsonObject j){
        if (j.has(ORDER)){
            return Optional.of(j.get(ORDER).getAsInt());
        }
        return Optional.empty();
    }
}
