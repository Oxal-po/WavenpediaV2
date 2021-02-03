package fr.oxal.v2.waven.entity.pvm.dungeon.chapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;

import java.util.ArrayList;
import java.util.Optional;

public interface WithChapters {

    String CHAPTERS = "chapters";

    Optional<JsonArray> getArrayChapters();

    default Optional<JsonArray> getArrayChapters(JsonObject j){
        return Optional.of((JsonArray) j.get(CHAPTERS));
    }

    default ArrayList<DungeonChapter> getChapters(){
        ArrayList<DungeonChapter> list = new ArrayList<>();

        getArrayChapters().ifPresent(a -> {
            for (JsonElement e : a){
                list.add(Wavenpedia.gson.fromJson(e, DungeonChapter.class));
            }
        });

        return list;
    }
}
