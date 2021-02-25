package fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.entity.pvm.drop.SingleObtainableItemListDefinition;
import fr.oxal.v2.waven.utils.collections.WavenEntities;

import java.util.ArrayList;
import java.util.List;

public interface Dropeable extends WavenInterface {

    List<SingleObtainableItemListDefinition> getDropZone();

    default List<SingleObtainableItemListDefinition> getDropZone(WavenEntity wavenEntity) {
        ArrayList<SingleObtainableItemListDefinition> list = new ArrayList<>();
        for (SingleObtainableItemListDefinition s : WavenEntities.getAll(SingleObtainableItemListDefinition.class, WavenEntity::isAvailable)) {
            if (s.contain(wavenEntity)) {
                list.add(s);
            }
        }
        return list;
    }

    default boolean canDrop() {
        return !getDropZone().isEmpty();
    }
}
