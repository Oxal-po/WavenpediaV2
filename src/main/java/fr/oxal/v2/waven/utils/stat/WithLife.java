package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;

import java.util.Optional;

public interface WithLife extends WithStat{

    Optional<Double> getLife(int level);
    default Optional<Double> getLife(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.LIFE));
    }

    default Optional<Double> getLifeModifier(int level){
        return getStatModifier((a, b) -> a + a*b/100, this::getLife, a -> a.isLifeModifier(), level);
    }
}
