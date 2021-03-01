package fr.oxal.v2.waven.entity.pvm.fight.dungeon;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.pvm.fight.AbstractFight;


public class DungeonZone extends AbstractFight {

    public final static String PATH = Wavenpedia.jsonPath + "DungeonZoneDefinition/";

    public DungeonZone(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH;
    }
}
