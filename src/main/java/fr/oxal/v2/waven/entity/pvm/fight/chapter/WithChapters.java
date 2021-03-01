package fr.oxal.v2.waven.entity.pvm.fight.chapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithChapters {

    String CHAPTERS = "chapters";

    Optional<JsonArray> getChaptersArray();

    default Optional<JsonArray> getChaptersArray(JsonObject json) {
        if (json.has(CHAPTERS)) {
            return Optional.of(json.get(CHAPTERS).getAsJsonArray());
        }

        return Optional.empty();
    }

    default List<Chapter> getChapters() {
        ArrayList<Chapter> list = new ArrayList();
        getChaptersArray().ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonObject()) {
                    list.add(new Chapter(e.getAsJsonObject()));
                }
            }
        });
        return list;
    }
}
