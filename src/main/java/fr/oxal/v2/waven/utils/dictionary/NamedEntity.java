package fr.oxal.v2.waven.utils.dictionary;

import fr.oxal.v2.utils.text.WavenParser;

public interface NamedEntity extends HaveDictionary {

    String BASE_STRING = "none";

    long getNameId();
    long getDescriptionId();

    String getName();
    String getDescription();

    default String getParsedName(){
        return getParsedName(0);
    }

    default String getParsedName(int level){
        return WavenParser.parse(getDescription(), this, level);
    }

    default String getParsedDescription(){
        return getParsedDescription(0);
    }

    default String getParsedDescription(int level){
        return WavenParser.parse(getDescription(), this, level);
    }

    default boolean haveName(){
        return getName() != null && !getName().equals(BASE_STRING) && getName().length() > 0;
    }

    default boolean haveDescription(){
        return getDescription() != null && !getDescription().equals(BASE_STRING);
    }

}
