package fr.oxal.v2.waven.utils.parser;

public abstract class Parser {

    public abstract void setup(Object... objects);
    public abstract String parse(String text);
    public abstract boolean canParse(String text);
}
