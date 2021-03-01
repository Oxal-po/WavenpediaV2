package fr.oxal.v2.waven.utils.parser.effect;

import fr.oxal.v2.waven.utils.parser.WavenEntityParser;
import fr.oxal.v2.waven.utils.parser.other.QuantityParser;
import fr.oxal.v2.waven.utils.parser.other.ValueParser;

import java.util.Optional;

public class ReferenceValueParser extends WavenEntityParser {

    public final static String REGEX_GLOBAL_VALUE = "\\{((?!\\.)[aA0-zZ9]+)!?\\}";
    public final static String VALUE_FORMAT = "%s(%d)";
    public final static QuantityParser quantityParser = new QuantityParser();
    public final static ValueParser valueParser = new ValueParser();

    @Override
    public String parse(String text) {
        String ref = text.replace("{", "").replace("}", "").replace("#", "");
        Optional<Integer> optional = getNamedEntity().asDynamicedEntity().getDynamicValue(ref, getLevel());
        if (optional.isPresent()) {
            return optional.get() + "";
        }
        System.err.println("erreur ReferenceValueParser : valeur introuvable : " + ref + " : " + getNamedEntity().asDynamicedEntity().getDynamicValues());
        return null;
    }

    @Override
    public boolean canParse(String text) {
        if (text.matches(REGEX_GLOBAL_VALUE)) {
            if (getNamedEntity().isDynamicedEntity()) {
                return true;
            } else {
                System.err.println("erreur ReferenceEffectParser : " + getNamedEntity().getName() + " n'est pas un DynamicedEntity");
            }
        }
        return false;
    }
}
