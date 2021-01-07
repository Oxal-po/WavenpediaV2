package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithPm extends WithStat{

    Optional<Double> getPm(int level);
    default Optional<Double> getPm(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.PM));
    }
}
