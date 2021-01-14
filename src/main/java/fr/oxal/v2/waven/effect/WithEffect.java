package fr.oxal.v2.waven.effect;

import com.google.gson.JsonElement;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.WithRefEntity;

import java.util.ArrayList;
import java.util.List;

public interface WithEffect extends WithRefEntity {

    default List<WavenEffect> getEffects(){
        ArrayList<WavenEffect> l = new ArrayList<>();
        getWavenRef().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonPrimitive() && e.getAsJsonPrimitive().isString()){
                    l.add(new WavenEffect(e.getAsString()));
                }
            }
        });
        return l;
    }

    default List<String> getNameEffects(){
        ArrayList<String> l = new ArrayList<>();
        getWavenRef().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonPrimitive() && e.getAsJsonPrimitive().isString()){
                    l.add(e.getAsString());
                }
            }
        });
        return l;
    }
}
