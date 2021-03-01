package fr.oxal.v2.utils.docMaker;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.entity.base.god.God;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.Dropeable;
import fr.oxal.v2.waven.utils.jsonCreator.FileMaker;

import java.io.IOException;

public class DocUtils {

    public static void SpellPerWeapon() {
        for (God w : WavenEntities.getAll(God.class, WavenEntity::isAvailable)) {
            for (Weapon a : w.getWeapons()) {
                System.out.println("\t" + a.getName());
                for (Spell s : a.getSpells()) {
                    System.out.println("\t\t" + s.getName().replace("\n", " ") + ";" + s.getGlobalParsedDescription(1).replace("\n", " "));
                }
            }
        }
    }

    public static void DropDoc() {
        for (Class<? extends Dropeable> c : Wavenpedia.ALL_DROPEABLE_CLASS) {
            StringBuilder builder = new StringBuilder();
            builder.append(c.getSimpleName() + "\n");
            for (Dropeable d : WavenEntities.getAll(c)) {
                if (d.isWavenEntity() && d.asWavenEntity().isAvailable() && d.canDrop()) {
                    builder.append(d.getDropText() + "\n");
                }
            }

            try {
                FileMaker.makeFile(builder.toString(), "out/doc/drop/" + c.getSimpleName() + ".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
