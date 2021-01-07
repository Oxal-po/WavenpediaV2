package fr.oxal.v2.utils.text;

import fr.oxal.v2.waven.WavenEntity;

public class WavenParser {

    public static String parse(String text){
        return parse(text, null, 1);
    }

    public static String parse(String text, WavenEntity waven){
        return parse(text, waven, 1);
    }

    public static String parse(String text, WavenEntity waven, int level){
        return text;
    }
}
