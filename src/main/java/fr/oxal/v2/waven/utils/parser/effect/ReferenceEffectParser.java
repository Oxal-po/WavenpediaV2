package fr.oxal.v2.waven.utils.parser.effect;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParsing;
import fr.oxal.v2.waven.utils.parser.other.QuantityParser;
import fr.oxal.v2.waven.utils.parser.other.ValueParser;
import fr.oxal.v2.waven.utils.parser.utils.OverrideableParser;
import fr.oxal.v2.waven.utils.parser.utils.ReferenceParser;
import fr.oxal.v2.waven.utils.parser.utils.WavenObjectParser;

import java.util.Optional;

import static fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithFilters.FILTERS;

public class ReferenceEffectParser extends WavenEntityParsing implements ReferenceParser<WavenEffect> {

    public final static String REGEX_GLOBAL_EFFECT = "\\{((?!\\.).*):((?!\\.)[aA0-zZ9]+)!?\\}";
    public final static String REGEX_GLOBAL_EFFECT_REF = "\\{([aA-zZ]+):([aA-zZ]+)\\.([0-9]+)\\.([aA-zZ]+)\\}";
    public final static String VALUE_NEXT_EFFECT = "valueAndNext";
    public final static String VALUE_EFFECT = "value";
    public final static String MECHANISM_SPAWN_RANGE = "MECHANISMSPAWNRANGE";
    public final static String DISTANCE = "distance";
    public final static String VALUE_FORMAT = "%s(%d)";
    public final static QuantityParser quantityParser = new QuantityParser();
    public final static ValueParser valueParser = new ValueParser();

    @Override
    public String parse(String text) {
        Optional<JsonObject> o = getJsonRef(text, getNamedEntity());
        if (o.isPresent()){
            Optional<WavenEffect> effect = getObject(text);
            Optional<Integer> i = getValue(text, getLevel(), getNamedEntity());
            if (effect.isPresent()){
                //parse de l'effet
                if (quantityParser.canParse(effect.get().getName())){
                    //si c'est un effet basé sur ça quantité
                    if (i.isPresent()){
                        quantityParser.setup(i.get());
                        return quantityParser.parse(effect.get().getName());
                    }else{
                        System.err.println("erreur ReferenceEffectParser : echec du calcul par reference : " + getJsonRef(text, getNamedEntity()));
                    }
                } else if (valueParser.canParse(effect.get().getName())){
                    //parser de valeur {0}
                    valueParser.setup(i.get());
                    return valueParser.parse(effect.get().getName());
                } else if (getNamedEntity().isWithFilters() && !effect.get().haveName()){
                    //parser si "l'effet" n'est pas trouver (utile que pour la rangeMeca actuellement)
                    if (effect.get().getKeyWord().equals(MECHANISM_SPAWN_RANGE)){
                        Optional<Integer> option = getNamedEntity().asWithFilters().getDynamicFilterValue(DISTANCE, getLevel(), getNamedEntity());
                        if (option.isPresent()){
                            return option.get() + "";
                        }
                    }
                } else if (effect.get().haveName()){
                    //parser pou les effet dit sans valeur mais qui en on quand même une au final parceque pourquoi pas
                    Optional<Integer> optional = getNamedEntity().asDynamicedEntity().getDynamicValue(getKeyRef(text), getLevel(), getNamedEntity());
                    if (optional.isPresent()){
                        return String.format(VALUE_FORMAT, effect.get().getName(), optional.get());
                    }

                    System.err.println("erreur ReferenceEffectParser : valeur introuvable : " + getKeyRef(text) + " : " + getNamedEntity().asDynamicedEntity().getDynamicValues());

                }else if (ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_EFFECT) || ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_NEXT_EFFECT) && i.isPresent()){
                    return i.get() + "";
                }else {
                    System.err.println("erreur ReferenceEffectParser : echec du parsing : " + effect.get().getName() + " : " + effect.get().getKeyWord());
                }
            }else if (ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_EFFECT) && i.isPresent()){
                return i.get() + "";
            }else{
                System.err.println("erreur ReferenceEffectParser : L'effet n'est pas trouver avec la ref : " + getKeyRef(text));
            }

        } else{
            System.err.println(getObject(text));
            System.err.println("erreur ReferenceEffectParser : La refference n'est pas présente dans l'objet : " + getKeyRef(text));
        }
        return null;
    }

    @Override
    public boolean canParse(String text) {
        if (text.matches(REGEX_GLOBAL_EFFECT)){
            if (getNamedEntity().isDynamicedEntity()){
                return true;
            }else{
                System.err.println("erreur ReferenceEffectParser : " + getNamedEntity().getName() + " n'est pas un DynamicedEntity");
            }
        }
        return false;
    }

    @Override
    public Optional<WavenEffect> getObject(String text) {
        String name = ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1);
        if (name.equals(VALUE_EFFECT)){
            return Optional.empty();
        }
        return Optional.of(new WavenEffect(ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).toUpperCase()));
    }

    @Override
    public String getKeyRef(String text) {
        return ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 2);
    }
}
