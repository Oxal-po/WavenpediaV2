package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithShield  extends WithStat{

    Optional<Double> getShield(int level);
    default Optional<Double> getShield(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.SHIELD));
    }
}
