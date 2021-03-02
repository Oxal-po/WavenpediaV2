package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface WithStat extends WavenInterface {

    String STATS = "stats";
    String KEY = "k";
    String VALUE = "v";

    int RES_PHY_ID = 76;
    int RES_MAG_ID = 75;
    int CC_PHY_ID = 16;
    int CC_MAG_ID = 17;
    int DMG_CC_PHY_ID = 18;
    int DMG_CC_MAG_ID = 78;

    default double getStat(int level, JsonObject jsonObject, String nameStat) {
        return WavenMath.getNumber((JsonObject) jsonObject.get(nameStat), level, this);
    }

    default Optional<Double> getStatModifier(BinaryOperator<Double> operator, Function<Integer, Optional<Double>> function, Predicate<WithPassiveModifiers.Modifier> mod, int level) {
        if (isWithSkills()) {
            return function.apply(level)
                    .map(a -> (double) Math.round(operator.apply(a, WithPassiveModifiers.getValueModifier(asWithSkills().getSkills()
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

    Optional<JsonArray> getStatsArray();

    default Optional<JsonArray> getStatsArray(JsonObject json) {
        if (json.has(STATS)) {
            return Optional.of(json.get(STATS).getAsJsonArray());
        }
        return Optional.empty();
    }

    default Optional<JsonObject> getStatObject(int id) {
        final Optional<JsonObject>[] o = new Optional[]{Optional.empty()};

        getStatsArray().ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonObject() && e.getAsJsonObject().has(KEY)) {
                    if (e.getAsJsonObject().get(KEY).getAsInt() == id) {
                        o[0] = Optional.of(e.getAsJsonObject());
                    }
                }
            }
        });

        return o[0];
    }

    default Optional<Integer> getStat(int id, int level) {
        final Optional<Integer>[] o = new Optional[]{Optional.empty()};
        getStatObject(id).ifPresent(a -> {
            if (a.has(VALUE)) {
                o[0] = Optional.of(WavenMath.getNumber(a, level, this));
            }
        });

        return o[0];
    }

    default Optional<Integer> getCCPhyChance(int level) {
        return getStat(CC_PHY_ID, level);
    }

    default Optional<Integer> getCCPhyDamage(int level) {
        return getStat(DMG_CC_PHY_ID, level);
    }

    default Optional<Integer> getCCMagChance(int level) {
        return getStat(CC_MAG_ID, level);
    }

    default Optional<Integer> getCCMagDamage(int level) {
        return getStat(DMG_CC_MAG_ID, level);
    }

    default Optional<Integer> getResPhy(int level) {
        return getStat(RES_PHY_ID, level);
    }

    default Optional<Integer> getResMag(int level) {
        return getStat(RES_MAG_ID, level);
    }
}
