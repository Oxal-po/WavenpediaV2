package fr.oxal.v2.utils.text;

import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.parser.effect.*;
import fr.oxal.v2.waven.utils.parser.entity.EntityParser;
import fr.oxal.v2.waven.utils.parser.other.GenderParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WavenParser {

    public static final EntityParser entityParser = new EntityParser();
    public static final EffectParser simpleEffectParser = new EffectParser();
    public static final MathematicEffectParser mathematicEffectParser = new MathematicEffectParser();
    public static final SymboleEffectParser symboleEffectParser = new SymboleEffectParser();
    public static final ReferenceEffectParser effectParser = new ReferenceEffectParser();
    public final static ReferenceValueEntityEffectParser refValEntity = new ReferenceValueEntityEffectParser();
    public final static GenderParser genderParser = new GenderParser();
    public final static int MARKDOWN = 4;
    public final static int GLOBAL = 3;
    public final static int DELETE_CONDI = 2;
    public final static int DELETE_BALISE = 1;
    public final static int NONE = 0;


    public static String parse(String text, int... option) {
        return parse(text, null, 1, option);
    }

    public static String parse(String text, NamedEntity waven, int... option) {
        return parse(text, waven, 1, option);
    }

    public static String parse(String text, NamedEntity waven, int level, int... option) {
        Pattern p = Pattern.compile("\\{((?!\\{).)*\\}");
        text = text.replace("\t", "");
        Matcher m = p.matcher(text);
        return parseOption(matcher(m, text, waven, level), option);
    }

    private static String matcher(Matcher m, String text, NamedEntity waven, int level) {
        while (m.find()) {
            String find = m.group();

            //setup est ici car j'en ai besoin dans le canParse
            effectParser.setup(waven, level);

            if (entityParser.canParse(find)) {
                //je check si entité avec id
                entityParser.setup(waven, level);
                text = text.replace(find, entityParser.parse(find));
            } else if (simpleEffectParser.canParse(find)) {
                //je check si c'est un effet simple
                simpleEffectParser.setup(waven, level);
                text = text.replace(find, simpleEffectParser.parse(find));
            } else if (effectParser.canParse(find)) {
                //je check si c'est un effet a valeur (et donc a reférence)
                text = text.replace(find, effectParser.parse(find));
            } else if (refValEntity.canParse(find)) {
                //je check si c'est un effet a valeur (et donc a reférence)
                text = text.replace(find, refValEntity.parse(find));
            } else if (mathematicEffectParser.canParse(find)) {
                //je check si c'est un effet avec calcul
                mathematicEffectParser.setup(waven, level);
                text = text.replace(find, mathematicEffectParser.parse(find));
            } else if (symboleEffectParser.canParse(find)) {
                //je check si c'est un effet avec un symbole
                symboleEffectParser.setup(waven, level);
                text = text.replace(find, symboleEffectParser.parse(find));
            }  else if (genderParser.canParse(find)) {
                //je check si c'est en fonction du genre
                text = text.replace(find, genderParser.parse(find));
            } else {
                System.err.println("-ERROR- erreur WavenParser : ce text n'est pas parser : " + waven.getClass().getSimpleName() + " : " + text + " : " + find);
                if (waven.isWavenEntity()) {
                    System.err.println(waven.asWavenEntity().getId());
                }
                if (waven.isDynamicedEntity()) {
                    System.err.println(waven.asDynamicedEntity().getDynamicValues());
                }
            }
        }

        return text;
    }

    public static String parseOption(String text, int... option){
        String str = text;
        Pattern pattern;
        Matcher matcher;
        for (int i : option){
            switch (i){
                case 0:
                    str = text;
                    break;
                case 1:
                    str = str.replaceAll("\\<((?!\\>).)*\\>", " ");

                    break;
                case 2:
                    str = text.replaceAll("\\<if fight\\>.*\\</if\\>", "");
                    break;
                case 3:
                    str = str.replaceAll("\\\\n", "\n")
                            .replaceAll("\\\\_", " ")
                            .replaceAll("\\\\-\\-\\-", "")
                            .replaceAll("\\\\v", "");
                    break;
                case 4:
                    str = str.replaceAll("<b>", "**")
                            .replaceAll("</b>", "**")
                            .replaceAll("<B>", "**")
                            .replaceAll("</B>", "**");
                    break;
            }
        }

        return str.trim();
    }

}
