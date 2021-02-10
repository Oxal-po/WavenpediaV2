package fr.oxal.v2.waven.utils.parser.effect;

import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParser;

import java.util.Optional;

public class MathematicEffectParser extends WavenEntityParser {

    public static final String REGEX_MATH = "\\{[aA-zZ]+:\\(?(\\-?[aA0-zZ9]+)([/\\*\\+\\-])(\\-?[aA0-zZ9]+)\\)?\\}";
    public static final String REGEX_NUMBER = "([\\+\\-]?[0-9]+)";

    @Override
    public String parse(String text) {
        return getValueEffect(text) + "";
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(REGEX_MATH);
    }

    public String getKeyEffect(String text){
        String t = ParserUtils.getText(text, REGEX_MATH, 1);
        if (t.matches(REGEX_NUMBER)){
            t = ParserUtils.getText(text, REGEX_MATH, 3);
        }
        return t;
    }

    public String getNumber(String text){
        String t = ParserUtils.getText(text, REGEX_MATH, 1);
        if (!t.matches(REGEX_NUMBER)){
            t = ParserUtils.getText(text, REGEX_MATH, 3);
        }
        return t;
    }

    public String getSymbole(String text){
        return ParserUtils.getText(text, REGEX_MATH, 2);
    }

    public int getValueEffect(String text){
        if (getNamedEntity().isDynamicedEntity()){
            Optional<Integer> o = getNamedEntity().asDynamicedEntity().getDynamicValue(getKeyEffect(text), getLevel());
            if (o.isPresent()){
                return (int) Math.round(WavenMath.calc(o.get(), Double.parseDouble(getNumber(text)), getSymbole(text)));
            }
            System.err.println("erreur  MathematicEffectParser : Dynamique value introuvable : " + text + " : " + getKeyEffect(text));
        }
        return -1000;
    }
}
