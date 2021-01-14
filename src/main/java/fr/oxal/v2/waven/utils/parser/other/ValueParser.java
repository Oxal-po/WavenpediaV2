package fr.oxal.v2.waven.utils.parser.other;

import fr.oxal.v2.waven.utils.parser.Parser;
import fr.oxal.v2.waven.utils.parser.ParserUtils;

import java.util.ArrayList;

public class ValueParser extends Parser {

    private ArrayList<Integer> values;
    public static final String REGEX_GLOBAL_VALUE = ".*\\{([0-9]+)\\}.*";

    @Override
    public void setup(Object... objects) {
        values = new ArrayList<>();
        for (Object o : objects){
            if (o instanceof Integer){
                values.add((int) o);
            }else{
                System.err.println("erreur QuantityParser : mauvais typage " + objects[0]);
            }
        }
    }

    @Override
    public String parse(String text) {
        for (int i = 0; i<values.size(); i++){
            text = ParserUtils.valueParsing(text, values.get(i), i);
        }
        return text;
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(REGEX_GLOBAL_VALUE);
    }
}
