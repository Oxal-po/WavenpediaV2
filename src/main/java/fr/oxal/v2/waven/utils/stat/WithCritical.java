package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithCritical extends WithStat {

    Optional<Double> getCriticalDamage(int level);
    Optional<Double> getCriticalChance(int level);

    default Optional<Double> getCriticalDamage(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.CRIT_DMG));
    }
    default Optional<Double> getCriticalChance(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.CRIT_CHANCE));
    }

    default Optional<Double> getCriticalDamageModifier(int level){
        return getStatModifier((a, b) -> a + b, this::getCriticalDamage, a -> a.isCritDamageModifier(), level);
    }

    default Optional<Double> getCriticalChanceModifier(int level){
        return getStatModifier((a, b) -> (a*10 + b)/10, this::getCriticalChance, a -> a.isCritChanceModifier(), level);
    }
}
