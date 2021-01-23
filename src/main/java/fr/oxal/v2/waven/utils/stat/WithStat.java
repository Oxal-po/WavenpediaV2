package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface WithStat extends WavenInterface {

    default double getStat(int level, JsonObject jsonObject, String nameStat) {
        return WavenMath.getNumber((JsonObject) jsonObject.get(nameStat), level, this);
    }

    default Optional<Double> getStatModifier(BinaryOperator<Double> operator, Function<Integer, Optional<Double>> function, Predicate<WithPassiveModifiers.Modifier> mod, int level){
        if (isWithSkills()) {
            return function.apply(level).map(a -> (double) Math.round(operator.apply(a, WithPassiveModifiers.getValueModifier(asWithSkills().getSkills()
                    .stream()
                    .map(WithPassiveModifiers::getModifiers)
                    .reduce(new ArrayList<>(), (c, b) -> {
                        c.addAll(b);
                        return c;
                    }), mod)))
            );
        }
        return function.apply(level).map(a -> (double) Math.round(a));
    }
}
