package fr.oxal.v2.waven.utils.parser.effect;

import com.google.gson.JsonObject;
import fr.oxal.v2.utils.text.WavenParser;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParser;
import fr.oxal.v2.waven.utils.parser.other.QuantityParser;
import fr.oxal.v2.waven.utils.parser.other.ValueParser;
import fr.oxal.v2.waven.utils.parser.utils.ReferenceParser;

import java.util.Optional;

public class ReferenceEffectParser extends WavenEntityParser implements ReferenceParser<WavenEffect> {

    public final static String REGEX_GLOBAL_EFFECT = "\\{((?!\\.).*):((?!\\.)[aA0-zZ9]+)!?\\}";
    public final static String REGEX_GLOBAL_EFFECT_REF = "\\{([aA-zZ]+):([aA-zZ]+)\\.([0-9]+)\\.([aA-zZ]+)\\}";
    public final static String VALUE_NEXT_EFFECT = "valueAndNext";
    public final static String VALUE_EFFECT = "value";
    public final static String MECHANISM_SPAWN_RANGE = "MECHANISMSPAWNRANGE";
    public final static String DISTANCE = "distance";
    public final static String SPAWN_RANGE_MECA = "mechanismSpawnRange";
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
                    return WavenParser.parse(valueParser.parse(effect.get().getName()), effect.get());
                } else if (effect.get().haveName()){
                    //parser pou les effet dit sans valeur mais qui en on quand même une au final parceque pourquoi pas
                    Optional<Integer> optional = getNamedEntity().asDynamicedEntity().getDynamicValue(getKeyRef(text), getLevel());
                    if (optional.isPresent()){
                        return String.format(VALUE_FORMAT, effect.get().getName(), optional.get());
                    }

                    System.err.println("erreur ReferenceEffectParser : valeur introuvable : " + getKeyRef(text) + " : " + getNamedEntity().asDynamicedEntity().getDynamicValues());

                }else if (ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_EFFECT) || ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_NEXT_EFFECT) && i.isPresent()){
                    return i.get() + "";
                } else {
                    System.err.println("erreur ReferenceEffectParser : echec du parsing : " + effect.get().getName() + " : " + effect.get().getKeyWord());
                }
            } else if (ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_EFFECT) && i.isPresent()) {
                return i.get() + "";
            } else if (ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).equals(VALUE_NEXT_EFFECT) && i.isPresent()) {
                return i.get() + "";
            } else if (getNamedEntity().isWithFilters()) {
                //parser si "l'effet" n'est pas trouver (utile que pour la rangeMeca actuellement)
                if (getNameRef(text).toUpperCase().equals(MECHANISM_SPAWN_RANGE)) {
                    Optional<Integer> option = getNamedEntity().asWithFilters().getDynamicFilterValue(DISTANCE, getLevel());
                    if (option.isPresent()) {
                        return option.get() + "";
                    } else {
                        System.err.println("error ReferenceEffectParser");
                    }
                }
            } else if (getNamedEntity().isDynamicedEntity()) {
                return "" + getNamedEntity().asDynamicedEntity().getDynamicValue(getKeyRef(text), getLevel());
            } else {
                System.err.println("erreur ReferenceEffectParser : L'effet n'est pas trouver avec la ref : " + getKeyRef(text));
            }

        } else {
            if (getKeyRef(text).equals("limit") || getKeyRef(text).equals("c")) {
                System.err.println("-WARNING- changer ce système c'est foireux (valeur par defaut ?)");
                return 1 + "";
            }
            System.err.println(text);
            System.err.println(getNamedEntity().asDynamicedEntity().getDynamicValues());
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
        if (name.equals(VALUE_EFFECT) || !WavenEffect.exist(name.toUpperCase())) {
            return Optional.empty();
        }
        return Optional.of(new WavenEffect(ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1).toUpperCase()));
    }

    @Override
    public String getKeyRef(String text) {
        return ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 2);
    }

    public String getNameRef(String text) {
        return ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1);
    }
}
