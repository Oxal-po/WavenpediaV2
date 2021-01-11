package fr.oxal.v2.waven.utils.parser;

import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

import java.util.Optional;

public class SimpleEffectParser extends AbstractParser{

    public final static String REGEX_GLOBAL_EFFECT = "\\{#?([aA-zZ]+)(\\[.*\\])?\\}";
    public final static String REGEX_NOT_OVERRIDE_EFFECT = "\\{#?([aA-zZ]+)\\}";
    public final static String REGEX_OVERRIDE_EFFECT = "\\{#?[aA-zZ]+\\[(.*)\\]\\}";

    @Override
    public String getGlobalRegex() {
        return REGEX_GLOBAL_EFFECT;
    }

    @Override
    public String getNotOverrideRegex() {
        return REGEX_NOT_OVERRIDE_EFFECT;
    }

    @Override
    public String getOverrideRegex() {
        return REGEX_OVERRIDE_EFFECT;
    }

    @Override
    public String getText(String text) {
        Optional<String> o = CommonParser.getTextByRegex(text, REGEX_NOT_OVERRIDE_EFFECT, 1);
        if (o.isPresent()){
            return o.get();
        }
        return NamedEntity.BASE_STRING;
    }
}
