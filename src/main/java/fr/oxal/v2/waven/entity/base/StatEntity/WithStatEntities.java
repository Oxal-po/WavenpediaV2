package fr.oxal.v2.waven.entity.base.StatEntity;

import com.google.gson.JsonElement;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.entity.base.Companion;
import fr.oxal.v2.waven.entity.base.StatEntity.floorMechanism.FloorMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.mechanism.ObjectMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.summoning.Summoning;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.WithRefEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface WithStatEntities extends WithRefEntity {

    default List<WavenEntity> getStatEntities() {
        ArrayList<WavenEntity> entities = new ArrayList<>();

        getWavenRef().ifPresent(a -> {
            for (JsonElement e : a) {
                if (e.isJsonObject()) {
                    getEntityByRef(e.getAsJsonObject()).ifPresent(entity -> {
                        if (!entity.isSpell()) {
                            entities.add(entity);
                        }
                    });
                }
            }
        });

        return entities;
    }

    default List<Companion> getCompanions(){
        return getStatEntities()
                .stream()
                .filter(WavenInterface::isCompanion)
                .map(WavenInterface::asCompanion)
                .collect(Collectors.toList());
    }

    default List<Weapon> getWeapons(){
        return getStatEntities()
                .stream()
                .filter(WavenInterface::isWeapon)
                .map(WavenInterface::asWeapon)
                .collect(Collectors.toList());
    }

    default List<Summoning> getSummonings(){
        return getStatEntities()
                .stream()
                .filter(WavenInterface::isSummoning)
                .map(WavenInterface::asSummoning)
                .collect(Collectors.toList());
    }

    default List<ObjectMechanism> getObjectMechanisms(){
        return getStatEntities()
                .stream()
                .filter(WavenInterface::isObjectMechanism)
                .map(WavenInterface::asObjectMechanism)
                .collect(Collectors.toList());
    }

    default List<FloorMechanism> getFloorMechanisms(){
        return getStatEntities()
                .stream()
                .filter(WavenInterface::isFloorMechanism)
                .map(WavenInterface::asFloorMechanism)
                .collect(Collectors.toList());
    }

    default boolean hasStatEntity(){
        return !getStatEntities().isEmpty();
    }

    default boolean hasStatEntity(Class c){
        return getStatEntities()
                .stream()
                .anyMatch(a -> a.getClass().equals(c));
    }
}
