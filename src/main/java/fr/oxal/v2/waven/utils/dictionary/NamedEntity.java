package fr.oxal.v2.waven.utils.dictionary;

import fr.oxal.v2.utils.text.WavenParser;

import java.util.ArrayList;

import static fr.oxal.v2.utils.text.TextUtils.upperCaseFirst;

public interface NamedEntity extends HaveDictionary {

    String BASE_STRING = "none";

    long getNameId();
    long getDescriptionId();

    String getName();

    default String getFileNameConsitution(){
        String[] split = getName().split(" ");
        StringBuilder builder = new StringBuilder();

        for (int i = 0 ; i < split.length; i++){
            builder.append(upperCaseFirst(split[i]));
        }

        return builder.toString().replace("'", "");
    }

    String getDescription();

    default String getParsedName(){
        return getParsedName(0);
    }

    default String getParsedName(int level, int... option){
        return WavenParser.parse(getName(), this, level, option);
    }

    default String getMardownParsedName(int level){
        return getParsedName(level, WavenParser.MARKDOWN, WavenParser.DELETE_BALISE, WavenParser.GLOBAL);
    }

    default String getGlobalParsedName(int level){
        return getParsedName(level, WavenParser.DELETE_BALISE, WavenParser.GLOBAL);
    }

    default String getParsedDescription(){
        return getParsedDescription(0);
    }

    default String getParsedDescription(int level, int... option){
        return WavenParser.parse(getDescription(), this, level, option);
    }

    default String getMardownParseDescription(int level){
        return getParsedDescription(level, WavenParser.MARKDOWN, WavenParser.DELETE_BALISE, WavenParser.GLOBAL);
    }

    default String getGlobalParsedDescription(int level){
        return getParsedDescription(level, WavenParser.DELETE_BALISE, WavenParser.GLOBAL);
    }

    default boolean haveName(){
        return getName() != null && !getName().equals(BASE_STRING) && getName().length() > 0;
    }

    default boolean haveDescription(){
        return getDescription() != null && !getDescription().equals(BASE_STRING);
    }

}
