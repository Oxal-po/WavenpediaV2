package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;

import java.util.Optional;

public interface WithLife extends WithStat{

    Optional<Double> getLife(int level);
    default Optional<Double> getLife(int level, JsonObject jsonObject){
        return Optional.of(getStat(level, jsonObject, Stat.LIFE));
    }
}
