package fr.oxal.v2.waven.utils.parser.effect;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParser;
import fr.oxal.v2.waven.utils.parser.utils.ReferenceParser;
import fr.oxal.v2.waven.utils.parser.utils.SecondReferenceParser;

import java.util.Optional;

public class MathematicReferenceParser extends WavenEntityParser implements ReferenceParser<WavenEffect>, SecondReferenceParser<DynamicedEntity> {


    public final static String REGEX_GLOBAL_EFFECT = "\\{([aA-zZ]+):\\(?([aA-zZ]+)\\.([0-9]+)\\.([aA0-zZ9]+)([/\\*\\+\\-])(\\-?[aA0-zZ9]+)\\)?\\}";
    public static final String REGEX_NUMBER = "([\\+\\-]?[0-9]+)";
    public final static String VALUE_NEXT_EFFECT = "valueAndNext";
    public final static String VALUE_EFFECT = "value";


    @Override
    public String parse(String text) {
        return getSecondObject(text).map(a -> getValueEffect(text, a) + "").orElse(null);
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
        if (name.equals(VALUE_EFFECT)) {
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
        for (Class c : Wavenpedia.ALL_DYNAMICED_CLASS) {
            if (name.equalsIgnoreCase(c.getSimpleName())) {
                return c;
            }
        }
        System.err.println("erreur ReferenceValueEntityEffectParser : aucune classe trouver avec le nom : " + name + " : " + text);
        return null;
    }

    public String getKeyWord(String text) {
        return ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 4);
    }

    public String getNumber(String text) {
        String t = ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 1);
        if (!t.matches(REGEX_NUMBER)) {
            t = ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 6);
        }
        return t;
    }

    public String getSymbole(String text) {
        return ParserUtils.getText(text, REGEX_GLOBAL_EFFECT, 5);
    }

    public int getValueEffect(String text, DynamicedEntity entity) {
        Optional<Integer> o = entity.getDynamicValue(getKeyWord(text), getLevel());
        if (o.isPresent()) {
            return (int) Math.round(WavenMath.calc(o.get(), Double.parseDouble(getNumber(text)), getSymbole(text)));
        }
        System.err.println("erreur  MathematicEffectParser : Dynamique value introuvable : " + text + " : " + getKeyWord(text));

        return -1000;
    }
}
