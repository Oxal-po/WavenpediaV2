package fr.oxal.v2.waven.utils.parser.other;

import fr.oxal.v2.waven.utils.parser.Parser;

public class GenderParser extends Parser {

    public static final String FORMAT_GENDER = "\\{heroGender: m\\[(.+)\\] f\\[(.+)\\]\\}";
    @Override
    public void setup(Object... objects) {

    }

    @Override
    public String parse(String text) {
        return "votre héros";
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(FORMAT_GENDER);
    }
}
