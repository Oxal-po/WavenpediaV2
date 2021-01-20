package fr.oxal.v2.waven.entity.base.god;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.FamiliesEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithGods extends FamiliesEntity {

    default List<Integer> getGodIds(){
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

    default ArrayList<God> getGods(){
        ArrayList<God> gods = new ArrayList<>();

        for (Integer i : getGodIds()){
            getGod(i).ifPresent(g -> gods.add(g));
        }

        return gods;
    }

    default boolean haveGod(){
        return getGodIds().size() > 1;
    }

    default Optional<God> getGod(int i){
        int g = getGodIds().get(i).intValue();

        if (g != 0 && God.fileExist(g, God.PATH_GOD)){
            return Optional.of(new God(g));
        }

        return Optional.empty();
    }
}
