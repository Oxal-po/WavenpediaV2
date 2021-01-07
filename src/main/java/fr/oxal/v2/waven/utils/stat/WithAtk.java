package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithAtk extends WithStat{

    Optional<Double> getAtk(int level);
    default Optional<Double> getAtk(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.ATTACK));
    }
}
