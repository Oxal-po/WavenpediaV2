package fr.oxal.v2.waven.utils.jsonArgumentEntity.rarity;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface WithRarity extends WavenInterface {

    String RARITY = "rarity";

    Optional<Integer> getRarity();

    default Optional<Integer> getRarity(JsonObject j) {
        if (j.has(RARITY)) {
            return Optional.of(j.get(RARITY).getAsInt());
        }
        return Optional.empty();
    }

    default boolean isCommon() {
        return getRarity().map(a -> a == 0).orElse(false);
    }

    default boolean isUncommon() {
        return getRarity().map(a -> a == 1).orElse(false);
    }

    default boolean isRare() {
        return getRarity().map(a -> a == 2).orElse(false);
    }

    default boolean isLegendary() {
        return getRarity().map(a -> a == 4).orElse(false);
    }
}
