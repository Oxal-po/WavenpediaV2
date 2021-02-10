package fr.oxal.v2.waven.utils.parser.effect;

import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParser;

import java.util.Optional;

public class SymboleEffectParser extends WavenEntityParser {

    public static final String REGEX_MATH = "\\{[aA-zZ]+:\\(?([\\+\\-])([aA0-zZ9]+)\\)?\\}";
    @Override
    public String parse(String text) {
        return getValueEffect(text) + "";
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(REGEX_MATH);
    }

    public String getKeyEffect(String text){
        return ParserUtils.getText(text, REGEX_MATH, 2);
    }

    public String getSymbole(String text){
        return ParserUtils.getText(text, REGEX_MATH, 1);
    }

    public int getValueEffect(String text){
        if (getNamedEntity().isDynamicedEntity()){
            Optional<Integer> o = getNamedEntity().asDynamicedEntity().getDynamicValue(getKeyEffect(text), getLevel());
            if (o.isPresent()){
                return  o.get()*-1;
            }
            System.err.println("erreur  SymboleEffectParser : Dynamique value introuvable : " + text + " : " + getKeyEffect(text));
        }
        return -1000;
    }
}
