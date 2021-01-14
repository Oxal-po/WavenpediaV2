package fr.oxal.v2.utils.text;

import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.parser.effect.EffectParser;
import fr.oxal.v2.waven.utils.parser.effect.ReferenceEffectParser;
import fr.oxal.v2.waven.utils.parser.effect.ReferenceValueEntityEffectParser;
import fr.oxal.v2.waven.utils.parser.entity.EntityParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WavenParser {

    public static final EntityParser entityParser = new EntityParser();
    public static final EffectParser simpleEffectParser = new EffectParser();
    public static final ReferenceEffectParser effectParser = new ReferenceEffectParser();
    public final static ReferenceValueEntityEffectParser refValEntity = new ReferenceValueEntityEffectParser();


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

            //setup est ici car j'en ai besoin dans le canParse
            effectParser.setup(waven, level);

            if (entityParser.canParse(find)){
                //je check si entité avec id
                entityParser.setup(waven, level);
                text = text.replace(find, entityParser.parse(find));
            }else if (simpleEffectParser.canParse(find)){
                //je check si c'est un effet simple
                simpleEffectParser.setup(waven, level);
                text = text.replace(find, simpleEffectParser.parse(find));
            } else if (effectParser.canParse(find)){
                //je check si c'est un effet a valeur (et donc a reférence)
                text = text.replace(find, effectParser.parse(find));
            }  else if (refValEntity.canParse(find)){
                //je check si c'est un effet a valeur (et donc a reférence)
                text = text.replace(find, refValEntity.parse(find));
            } else {
                System.err.println("erreur WavenParser : ce text n'est pas parser : " + text + " : " + find);
                System.err.println(waven.asDynamicedEntity().getDynamicValues());
            }
        }

        return text;
    }


}
