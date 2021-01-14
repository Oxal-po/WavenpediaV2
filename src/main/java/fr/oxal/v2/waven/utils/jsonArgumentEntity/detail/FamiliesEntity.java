package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.DetailsEntity;

public interface FamiliesEntity extends DetailsEntity {

    String FAMILIES = "families";

    default JsonArray getFamilies(JsonObject j){
        return (JsonArray) getDetails(j).get(FAMILIES);
    }

    JsonArray getFamilies();
}
