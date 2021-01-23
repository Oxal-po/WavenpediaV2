package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;

import java.util.Optional;

public interface WithPm extends WithStat{

    Optional<Double> getPm(int level);
    default Optional<Double> getPm(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.PM));
    }

    default Optional<Double> getPmModifier(int level){
        return getStatModifier((a, b) -> a + b, this::getPm, a -> a.isPmModifier(), level);
    }
}
