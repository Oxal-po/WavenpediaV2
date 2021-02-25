package fr.oxal.v2.waven.entity.base.god;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.WithWeapon;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.skill.WithElementarySkills;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.order.WithOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class God extends NamedWavenEntity implements WithSpells, WithWeapon,
        WithElementarySkills, WithOrder {

    public static final String PATH_GOD = Wavenpedia.jsonPath + "GodDefinition/";

    public God(int id) {
        super(id);
    }

    @Override
    public String getDescription() {
        return BASE_STRING;
    }

    @Override
    public String getPathFolder() {
        return PATH_GOD;
    }

    @Override
    public String getNameDictionnaire() {
        return GOD;
    }


    //todo faire equipeable pour finir ce getSpells -> getAll(Spell) -> getGods -> add
    @Override
    public List<Spell> getSpells() {
        return new ArrayList<>(WavenEntities
                .getAll(Spell.class, a -> a.isAvailable() && a.isEquipeable() && a.getFamilyIds().contains(getId())));
    }

    @Override
    public List<Integer> getIdSpells() {
        return WavenEntities
                .getAll(Spell.class, a -> a.isAvailable() && a.isEquipeable() && a.getFamilyIds().contains(getId()))
                .stream()
                .map(WavenEntity::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getWeaponsId() {
        return WavenEntities
                .getAll(Weapon.class, w -> w.getFamilyIds().contains(getId()))
                .stream()
                .map(w -> w.getId())
                .collect(Collectors.toList());
    }

    @Override
    public JsonObject getPrecomputeData() {
        return getPrecomputeData(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getElementarySkill() {
        return getElementarySkill(getJsonRepresentation());
    }

    @Override
    public Optional<Integer> getOrder() {
        return getOrder(getJsonRepresentation());
    }
}
