package fr.oxal.v2.waven.utils.parser.other;

import fr.oxal.v2.waven.utils.parser.Parser;
import fr.oxal.v2.waven.utils.parser.ParserUtils;

public class QuantityParser extends Parser {
    
    private int quantity;
    public static final String REGEX_GLOBAL_QUANTITY = "\\{ ?0, plural, one\\[#?(.*)\\] other\\[#?(.*)\\] ?\\}";
    
    @Override
    public void setup(Object... objects) {
        if (objects.length > 0){
            if (objects[0] instanceof Integer){
                quantity = (int) objects[0];
            }else{
                System.err.println("erreur QuantityParser : mauvais typage " + objects[0]);
            }
        }else{
            System.err.println("erreur QuantityParser : manque d'attribut dans le setup");
        }
    }

    @Override
    public String parse(String text) {
        if (quantity > 1){
            return quantity + ParserUtils.getText(text, REGEX_GLOBAL_QUANTITY, 2);
        }
        return quantity + ParserUtils.getText(text, REGEX_GLOBAL_QUANTITY, 1);
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(REGEX_GLOBAL_QUANTITY);
    }
}
