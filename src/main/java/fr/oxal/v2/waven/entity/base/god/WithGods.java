package fr.oxal.v2.waven.entity.base.god;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.FamiliesEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface WithGods extends FamiliesEntity {

    default List<God> getGods() {
        ArrayList<God> gods = new ArrayList<>();

        for (Integer i : getFamilyIds()) {
            if (God.fileExist(i, God.class)) {
                gods.add(new God(i));
            }
        }

        return gods;
    }

    default List<Integer> getIdGods() {
        return getGods().stream().map(WavenEntity::getId).collect(Collectors.toList());
    }

    default boolean haveGod() {
        return getFamilyIds().size() > 1;
    }

    default boolean hasGod(God g) {
        return hasFamily(g);
    }
}
