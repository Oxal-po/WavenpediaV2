package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.DetailsEntity;

import java.util.Optional;

public interface FamiliesEntity extends DetailsEntity {

    String FAMILIES = "families";

    default Optional<JsonArray> getFamilies(JsonObject j){
        return getDetails(j).map(a -> (JsonArray) a.get(FAMILIES));
    }

    Optional<JsonArray> getFamilies();
}
