package fr.oxal.v2.waven.utils.jsonArgumentEntity.parent;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.collections.WavenEntities;

import java.util.Optional;

public interface WithParent<T extends WavenInterface> extends WavenInterface {
    String PARENT = "parentDataId";

    int getParentId();
    Optional<T> getParent();
    default int getParentId(JsonObject j){
        return j.get(PARENT).getAsInt();
    }

    default Optional<T> getParent(Class c){
        return WavenEntities.construct(c, getParentId());
    }
}
