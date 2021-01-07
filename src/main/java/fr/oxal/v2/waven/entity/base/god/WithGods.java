package fr.oxal.v2.waven.entity.base.god;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.FamiliesEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithGods extends FamiliesEntity {

    default List<Double> getGodIds(){
        return new Gson().fromJson(getFamilies(), ArrayList.class);
    }

    default ArrayList<God> getGods(){
        ArrayList<God> gods = new ArrayList<>();

        for (int i = 0; i<getGodIds().size(); i++){
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
