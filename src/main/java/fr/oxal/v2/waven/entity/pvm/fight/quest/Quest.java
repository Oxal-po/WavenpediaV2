package fr.oxal.v2.waven.entity.pvm.fight.quest;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.pvm.fight.AbstractFight;

public class Quest extends AbstractFight {


    public final static String PATH = Wavenpedia.jsonPath + "QuestDefinition/";

    public Quest(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH;
    }
}
