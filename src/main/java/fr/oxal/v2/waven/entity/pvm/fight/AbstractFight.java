package fr.oxal.v2.waven.entity.pvm.fight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.entity.pvm.fight.chapter.Chapter;
import fr.oxal.v2.waven.entity.pvm.fight.chapter.WithChapters;
import fr.oxal.v2.waven.entity.pvm.fight.rang.WithRanks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractFight extends WavenEntity implements WithRanks, WithChapters {

    public AbstractFight(int id) {
        super(id);
    }

    @Override
    public Optional<JsonArray> getChaptersArray() {
        return getChaptersArray(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getRanksJson() {
        return getRanksJson(getJsonRepresentation());
    }

    @Override
    public int compareTo(WavenInterface wavenEntity) {
        return 0;
    }

    public List<Chapter> getChapters(WavenEntity waven) {
        ArrayList<Chapter> list = new ArrayList<>();

        for (Chapter c : getChapters()) {
            for (WavenEntity w : c.getEntityRewards()) {
            }
            if (c.contain(waven)) {
                list.add(c);
            }
        }
        return list;
    }
}
