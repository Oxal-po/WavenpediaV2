package fr.oxal.v2.waven.utils.parser.effect;

import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParsing;
import fr.oxal.v2.waven.utils.parser.utils.ReferenceParser;
import fr.oxal.v2.waven.utils.parser.utils.SecondReferenceParser;

import java.util.Optional;

import static fr.oxal.v2.waven.utils.parser.effect.ReferenceEffectParser.*;

public class ReferenceValueEntityEffectParser extends WavenEntityParsing implements ReferenceParser<WavenEffect>, SecondReferenceParser<DynamicedEntity> {

    public final static String REGEX_GLOBAL_EFFECT = "\\{([aA-zZ]+):([aA-zZ]+)\\.([0-9]+)\\.([aA0-zZ9]+)\\}";
    public final static String VALUE_NEXT_EFFECT = "valueAndNext";
    public final static String VALUE_EFFECT = "value";

    @Override
    public String parse(String text) {
        Optional<DynamicedEntity> entity = getSecondObject(text);
        if (entity.isPresent()){
            Optional<JsonObject> o = getJsonRef(text, entity.get());
            if (o.isPresent()){
                Optional<WavenEffect> effect = getObject(text);
                System.out.println(text);
                Optional<Integer> i = getValue(text, getLevel(), entity.get());
                if (effect.isPresent()){
                    //parse de l'effet
                    if (quantityParser.canParse(effect.get().getName())){
                        //si c'est un effet basé sur ça quantité
                        if (i.isPresent()){
                            quantityParser.setup(i.get());
                            return quantityParser.parse(effect.get().getName());
                        }else{
                            System.err.println("erreur ReferenceValueEntityEffectParser : echec du calcul par reference : " + getJsonRef(text, getNamedEntity()));
                        }
                    } else if (valueParser.canParse(effect.get().getName())){
                        valueParser.setup(i.get());
                        return valueParser.parse(effect.get().getName());
                    }else if (effect.get().haveName()){
                        //parser pou les effet dit sans valeur mais qui en on quand même une au final parceque pourquoi pas
                        System.out.println(getNamedEntity());
                        Optional<Integer> optional = getSecondObject(text).get().asDynamicedEntity().getDynamicValue(getKeyRef(text), getLevel());
                        if (optional.isPresent()){
                            return String.format(VALUE_FORMAT, effect.get().getName(), optional.get());
                        }

                        System.err.println("erreur ReferenceValueEntityEffectParser : valeur introuvable : " + getKeyRef(text) + " : " + getNamedEntity().asDynamicedEntity().getDynamicValues());

                    }else if ((ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_EFFECT) || ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_NEXT_EFFECT)) && i.isPresent()){
                        return i.get() + "";
                    }else {
                        System.err.println("erreur ReferenceValueEntityEffectParser : echec du parsing : " + effect.get().getName() + " : " + effect.get().getKeyWord());
                    }
                }else if ((ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_EFFECT) || ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_NEXT_EFFECT)) && i.isPresent()){
                    return i.get() + "";
                }else{
                    System.err.println("erreur ReferenceValueEntityEffectParser : L'effet n'est pas trouver avec la ref : " + getKeyRef(text));
                }

            }else{
                System.err.println("erreur ReferenceValueEntityEffectParser : La reference n'est pas présente dans l'objet : " + getKeyRef(text) );
            }
        }else{
            System.err.println("erreur ReferenceValueEntityEffectParser : Ref entity introuvable : " + text);
        }

        return null;
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(REGEX_GLOBAL_EFFECT);
    }

    @Override
    public String getKeyRef(String text) {
        return ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 4);
    }

    @Override
    public Optional<WavenEffect> getObject(String text) {
        String name = ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1);
        if (name.equals(VALUE_EFFECT)){
            return Optional.empty();
        }
        return Optional.of(new WavenEffect(name.toUpperCase()));
    }

    @Override
    public Optional<DynamicedEntity> getSecondObject(String text) {
        return WavenEntities.construct(getClassSecondObject(text), getIdSecondObject(text));
    }

    @Override
    public int getIdSecondObject(String text) {
        return Integer.parseInt(ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 3));
    }

    @Override
    public String getClassNameSecondObject(String text) {
        return ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 2);
    }

    @Override
    public Class<DynamicedEntity> getClassSecondObject(String text) {
        String name = getClassNameSecondObject(text);
        for (Class c : Wavenpedia.ALL_DYNAMICED_CLASS){
            if (name.equalsIgnoreCase(c.getSimpleName())){
                return c;
            }
        }
        System.err.println("erreur ReferenceValueEntityEffectParser : aucune classe trouver avec le nom : " + name);
        return null;
    }
}
