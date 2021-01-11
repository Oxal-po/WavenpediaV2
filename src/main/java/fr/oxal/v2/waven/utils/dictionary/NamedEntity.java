package fr.oxal.v2.waven.utils.dictionary;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.utils.text.WavenParser;
import fr.oxal.v2.waven.WavenEntity;

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
}
