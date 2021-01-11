package fr.oxal.v2.waven.effect;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.utils.dictionary.DictionaryFabric;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

import java.util.Optional;

public class WavenEffect implements NamedEntity {

    private String keyWord;

    public final static String NAME_EFFECT = "m_i18nNameId";
    public final static String DESCRI_EFFECT = "m_i18nDescriptionId";
    public final static long NAME_EFFECT_BASE = 0;
    public final static long DESCRI_EFFECT_BASE = 0;
    public final static String EFFECT_BASE = "{\n" +
            "    \"m_i18nNameId\": 0,\n" +
            "    \"m_i18nDescriptionId\": 0\n" +
            "  }";

    public WavenEffect(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public static JsonObject getKeyWordFile(){
        return DictionaryFabric.getDictionary(Wavenpedia.class, Wavenpedia.keyWordPath + "KeyWord.json");
    }

    public Optional<JsonObject> getEffectJson(){
        if (!haveKeyWord()){
            return Optional.empty();
        }else if (getKeyWordFile().has(getKeyWord())){
            return Optional.of(getKeyWordFile().get(getKeyWord()).getAsJsonObject());
        }
        System.err.println(getKeyWord());
        return Optional.empty();
    }

    @Override
    public long getNameId() {
        return getEffectJson().orElse(new Gson().fromJson(EFFECT_BASE, JsonObject.class)).get(NAME_EFFECT).getAsLong();
    }

    @Override
    public long getDescriptionId() {
        return getEffectJson().orElse(new Gson().fromJson(EFFECT_BASE, JsonObject.class)).get(DESCRI_EFFECT).getAsLong();
    }

    @Override
    public String getName() {
        if (!haveName()){
            return BASE_STRING;
        }
        return getDictionary().get(getNameId()+"").getAsString();
    }

    @Override
    public String getDescription() {
        if (!haveDescription()){
            return BASE_STRING;
        }
        return getDictionary().get(getDescriptionId()+"").getAsString();
    }

    @Override
    public String getNameDictionnaire() {
        return EFFECTS;
    }

    @Override
    public JsonObject getDictionary() {
        return DictionaryFabric.getDictionary(WavenEffect.class, Wavenpedia.dictionaryPath + EFFECTS);
    }

    @Override
    public String getText(long id) {
        return getDictionary().get(id + "").getAsString();
    }

    public boolean haveKeyWord(){
        return getKeyWord() != null;
    }

    public boolean haveName(){
        return getNameId() != NAME_EFFECT_BASE;
    }

    public boolean haveDescription(){
        return getDescriptionId() != DESCRI_EFFECT_BASE;
    }
}
