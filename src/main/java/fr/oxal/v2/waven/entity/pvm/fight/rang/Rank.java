package fr.oxal.v2.waven.entity.pvm.fight.rang;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.dictionary.DictionaryFabric;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

public class Rank implements NamedEntity {

    public static final String START_CHAPTER = "startsAtChapter";
    public static final String[] NAMES_RANG = new String[]{"bronze", "silver", "gold", "platinum", "diamond"};
    private final JsonObject json;

    public Rank(JsonObject json) {
        this.json = json;
    }

    @Override
    public long getNameId() {
        return 0;
    }

    @Override
    public long getDescriptionId() {
        return json.get("i18nDescriptionId").getAsLong();
    }

    @Override
    public String getName() {
        return "RANG";
    }

    @Override
    public String getDescription() {
        return getText(getDescriptionId());
    }

    @Override
    public String getNameDictionnaire() {
        return ADVENTURE;
    }

    @Override
    public JsonObject getDictionary() {
        return DictionaryFabric.getDictionary(getClass(), getNameDictionnaire());
    }

    @Override
    public String getText(long id) {
        return getDictionary().get(id + "").getAsString();
    }

    public int getStartChapter() {

        if (json.has(START_CHAPTER)) {
            return json.get(START_CHAPTER).getAsInt();
        }

        return 0;
    }

    @Override
    public int compareTo(WavenInterface wavenEntity) {
        return 0;
    }
}
