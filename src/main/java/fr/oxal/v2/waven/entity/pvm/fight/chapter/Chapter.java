package fr.oxal.v2.waven.entity.pvm.fight.chapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.entity.pvm.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Chapter implements WavenInterface {

    public static final String TYPE = "type";
    public static final String CHAPTER_INDEX = "chapterIndex";
    public static final String DURATION = "duration";
    public static final String DIFFICULTY = "difficulty";
    public static final String DESCRI = "i18nChapterDescriptionId";
    public static final String REWARD = "rewards";

    private final JsonObject json;

    public Chapter(JsonObject json) {
        this.json = json;
    }

    public Optional<Integer> getDuration() {
        if (json.has(DURATION)) {
            return Optional.of(json.get(DURATION).getAsInt());
        }
        return Optional.empty();
    }

    public Optional<Integer> getDifficulty() {
        if (json.has(DIFFICULTY)) {
            return Optional.of(json.get(DIFFICULTY).getAsInt());
        }
        return Optional.empty();
    }

    public Optional<Integer> getChapterIndex() {
        if (json.has(CHAPTER_INDEX)) {
            return Optional.of(json.get(CHAPTER_INDEX).getAsInt());
        }
        return Optional.empty();
    }

    public Optional<JsonArray> getRewardsArray() {
        if (json.has(REWARD)) {
            return Optional.of(json.get(REWARD).getAsJsonArray());
        }

        return Optional.empty();
    }

    public List<Item> getRewards() {
        ArrayList<Item> list = new ArrayList<>();

        getRewardsArray().ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonObject()) {
                    Item.getItemByJson(e.getAsJsonObject()).ifPresent(i -> {
                        list.add(i);
                    });
                }
            }
        });
        return list;
    }

    public List<WavenEntity> getEntityRewards() {
        ArrayList<WavenEntity> list = new ArrayList<>();

        for (Item i : getRewards()) {
            i.getEntity().ifPresent(a -> {
                list.add(a);
            });
        }

        return list;
    }

    public boolean contain(WavenEntity waven) {
        return getEntityRewards().contains(waven);
    }

    @Override
    public String toString() {
        return getChapterIndex().toString();
    }

    @Override
    public int compareTo(WavenInterface wavenEntity) {
        return 0;
    }
}
