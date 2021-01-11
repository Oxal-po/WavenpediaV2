package fr.oxal.v2.waven.utils.parser;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonParser {


    public static Optional<String> getTextByRegex(String text, String regex, int group){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()){
            return Optional.of(m.group(group));
        }
        return Optional.empty();
    }
}
