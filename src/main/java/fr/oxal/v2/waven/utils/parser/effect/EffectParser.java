package fr.oxal.v2.waven.utils.parser.effect;

import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParsing;
import fr.oxal.v2.waven.utils.parser.utils.OverrideableParser;
import fr.oxal.v2.waven.utils.parser.utils.WavenObjectParser;

import java.util.Optional;

public class EffectParser extends WavenEntityParsing implements OverrideableParser, WavenObjectParser<WavenEffect> {

    public final static String REGEX_GLOBAL_EFFECT = "\\{#?%?([aA-zZ]+)(\\[.*\\])?!?\\}";
    public final static String REGEX_NOT_OVERRIDE_EFFECT = "\\{#?%?([aA-zZ]+)!?\\}";
    public final static String REGEX_OVERRIDE_EFFECT = "\\{#?%?[aA-zZ]+\\[(.*)\\]!?\\}";


    @Override
    public String parse(String text) {
        if (isOverride(text)) {
            return getOverride(text);
        }
        return getObject(text).get().getName().replaceAll("\\{[0-9]+\\}", "");
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(REGEX_GLOBAL_EFFECT);
    }

    @Override
    public boolean isOverride(String text) {
        return text.matches(REGEX_OVERRIDE_EFFECT);
    }

    @Override
    public String getOverride(String text) {
        return ParserUtils.getText(text, REGEX_OVERRIDE_EFFECT, 1);
    }

    @Override
    public Optional<WavenEffect> getObject(String text) {
        return Optional.of(new WavenEffect(ParserUtils.getText(text, REGEX_NOT_OVERRIDE_EFFECT, 1).toUpperCase()));
    }
}
