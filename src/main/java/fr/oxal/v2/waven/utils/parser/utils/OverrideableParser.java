package fr.oxal.v2.waven.utils.parser.utils;

public interface OverrideableParser {

    boolean isOverride(String text);
    String getOverride(String text);
}
