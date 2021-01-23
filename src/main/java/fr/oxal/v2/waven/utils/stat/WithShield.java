package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.passif.WithPassiveModifiers;

import java.util.Optional;

public interface WithShield  extends WithStat{
    Optional<Double> getShield(int level);
    default Optional<Double> getShield(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.SHIELD));
    }
}
