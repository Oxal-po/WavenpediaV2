package fr.oxal.v2.utils.docMaker;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.entity.base.god.God;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.utils.collections.WavenEntities;

public class DocUtils {

    public static void SpellPerWeapon() {
        for (God w : WavenEntities.getAll(God.class, WavenEntity::isAvailable)) {
            System.out.println(w.getGlobalParsedName(0));
            for (Weapon a : w.getWeapons()) {
                System.out.println("\t" + a.getName());
                for (Spell s : a.getSpells()) {
                    System.out.println("\t\t" + s.getName().replace("\n", " ") + ";" + s.getGlobalParsedDescription(1).replace("\n", " "));
                }
            }
        }
    }
}
