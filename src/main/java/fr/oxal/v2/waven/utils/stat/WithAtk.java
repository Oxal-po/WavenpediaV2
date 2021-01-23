package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public interface WithAtk extends WithStat {

    Optional<Double> getAtk(int level);
    default Optional<Double> getAtk(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.ATTACK));
    }

    default Optional<Double> getAtkModifier(int level){
        return getStatModifier((a, b) -> a + a*b/100, this::getAtk, a -> a.isAtkModifier(), level);
    }
}
