package fr.oxal.v2.waven.entity.base.god;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.WithWeapon;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.skill.WithElementarySkills;
import fr.oxal.v2.waven.utils.collections.WavenEntities;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class God extends NamedWavenEntity implements WithSpells, WithWeapon, WithElementarySkills {

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
        return WavenEntities
                .getAll(Spell.class, a -> a.isAvailable() && a.isEquipeable() && a.getFamilyIds().contains((double) getId()))
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getIdSpells() {
        return WavenEntities
                .getAll(Spell.class, a -> a.isAvailable() && a.isEquipeable() && a.getFamilyIds().contains((double) getId()))
                .stream()
                .map(a -> (double) a.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getWeaponsId() {
        return WavenEntities
                .getAll(Weapon.class, w -> w.getFamilyIds().contains((double) getId()))
                .stream()
                .map(w -> (double) w.getId())
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
}
