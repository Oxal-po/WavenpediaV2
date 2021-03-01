package fr.oxal.v2.waven.entity.pvm.fight.rang;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithRanks {

    String RANKS = "ranks";

    Optional<JsonObject> getRanksJson();

    default Optional<JsonObject> getRanksJson(JsonObject json) {
        if (json.has(RANKS)) {
            return Optional.of(json.get(RANKS).getAsJsonObject());
        }

        return Optional.empty();
    }

    default List<Rank> getRanks() {
        ArrayList<Rank> list = new ArrayList();
        getRanksJson().ifPresent(a -> {
            for (String name : Rank.NAMES_RANG) {
                list.add(new Rank(a.get(name).getAsJsonObject()));
            }
        });
        return list;
    }
}
