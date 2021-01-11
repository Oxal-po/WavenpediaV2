package fr.oxal.v2.utils.text;

import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.parser.AbstractParser;
import fr.oxal.v2.waven.utils.parser.EntityParser;
import fr.oxal.v2.waven.utils.parser.SimpleEffectParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WavenParser {

    public static final EntityParser entityParser = new EntityParser();
    public static final SimpleEffectParser simpleEffectParser = new SimpleEffectParser();


    public static String parse(String text){
        return parse(text, null, 1);
    }

    public static String parse(String text, NamedEntity waven){
        return parse(text, waven, 1);
    }

    public static String parse(String text, NamedEntity waven, int level){

        Pattern p = Pattern.compile("\\{((?!\\{).)*\\}");
        Matcher m = p.matcher(text);
        return matcher(m, text, waven, level);
    }

    private static String matcher(Matcher m, String text, NamedEntity waven, int level) {
        while (m.find()){
            String find = m.group();
            if (entityParser.canParsing(find)){
                //je check si entité avec id
                text = text.replace(find, entityParser.parse(find));
            }else if (simpleEffectParser.canParsing(find)){
                //je check si c'est un effet simple
                text = text.replace(find, simpleEffectParser.parse(find));
            } else {

            }
        }

        return text;
    }

    public static String useParser(AbstractParser parser, String text, String find){
        if (parser.haveOverrideName(find)){
            //si il a une nom override donc dans []
            text = text.replace(find, parser.getOverideName(find).get());
        }else{
            text = text.replace(find, parser.parse(find));
        }
        return text;
    }


}
