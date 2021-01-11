package fr.oxal.v2.waven.utils.parser;

import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

import java.util.Optional;

import static fr.oxal.v2.waven.utils.parser.CommonParser.getTextByRegex;

public abstract class AbstractParser {

    public abstract String getGlobalRegex();
    public abstract String getNotOverrideRegex();
    public abstract String getOverrideRegex();
    public abstract String getText(String text);

    public String parse(String text){
        if (haveOverrideName(text)){
            //si il a une nom override donc dans []
            text = text.replace(text, getOverideName(text).get());
        }else{
            text = text.replace(text, getText(text));
        }
        return text;
    }

    public boolean haveOverrideName(String text){
        return text.matches(getOverrideRegex());
    }

    public Optional<String> getOverideName(String text){
        return getTextByRegex(text,getOverrideRegex(), 1);
    }

    public boolean canParsing(String text){
        return text.matches(getGlobalRegex());
    }
}
