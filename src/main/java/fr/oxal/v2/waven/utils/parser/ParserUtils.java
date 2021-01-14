package fr.oxal.v2.waven.utils.parser;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {


    public static Optional<String> getTextByRegex(String text, String regex, int group){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()){
            return Optional.of(m.group(group));
        }
        return Optional.empty();
    }

    public static String getText(String text, String regex, int group){
        Optional<String> o = ParserUtils.getTextByRegex(text, regex, group);
        if (o.isPresent()){
            return o.get();
        }else{
            System.err.println("erreur ParserUtils : parsing echec : " + text + " : " + regex + " : " + group);
        }
        return null;
    }

    public static String valueParsing(String text, int value, int id){
        return text.replaceAll("\\{"+ id + "\\}", value + "");
    }
}
