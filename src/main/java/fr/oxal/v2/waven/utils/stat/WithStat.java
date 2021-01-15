package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;
import fr.oxal.v2.waven.entity.WavenInterface;

public interface WithStat extends WavenInterface {

    default double getStat(int level, JsonObject jsonObject, String nameStat){
        return WavenMath.getNumber((JsonObject) jsonObject.get(nameStat), level, this);
    }
}
