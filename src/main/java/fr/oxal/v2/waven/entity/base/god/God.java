package fr.oxal.v2.waven.entity.base.god;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;

import java.util.List;

public class God extends NamedWavenEntity implements WithSpells {

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
    public List<Double> getSpells() {
        return null;
    }
}
