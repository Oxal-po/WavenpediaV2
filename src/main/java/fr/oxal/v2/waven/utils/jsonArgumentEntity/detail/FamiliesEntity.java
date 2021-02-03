package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.base.god.God;
import fr.oxal.v2.waven.entity.pvm.family.Family;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FamiliesEntity extends DetailsEntity {

    String FAMILIES = "families";

    default Optional<JsonArray> getFamilies(JsonObject j) {
        if (j.has(FAMILIES)){
            return Optional.of((JsonArray) j.get(FAMILIES));
        }
        return getDetails(j).map(a -> (JsonArray) a.get(FAMILIES));
    }

    Optional<JsonArray> getFamilies();

    default List<Integer> getFamilyIds(){
        ArrayList<Integer> list = new ArrayList<>();
        getFamilies().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonPrimitive()){
                    list.add(e.getAsInt());
                }
            }
        });
        return list;
    }

    default Optional<String> getNameFamily(int id){
        if (God.fileExist(id, God.class)){
            return Optional.of(new God(id).getName());
        }else if (Family.fileExist(id, Family.class)){
            return Optional.of(new Family(id).getDisplayName());
        }else{
            System.err.println("error FamiliesEntity : getNameFamily : id non reconnu : " + id + " : " + asNamedEntity().getName());
        }

        return Optional.empty();
    }

    default ArrayList<String> getNamesFamilies(){
        ArrayList<String> list = new ArrayList<>();

        for (Integer i : getFamilyIds()){
            getNameFamily(i).ifPresent(a -> list.add(a));
        }

        return list;
    }

    default boolean hasFamily(Family f){
        return getFamilyIds().stream().anyMatch(a -> a == f.getId());
    }

    default boolean hasFamily(God f){
        return getFamilyIds().stream().anyMatch(a -> a == f.getId());
    }
}
