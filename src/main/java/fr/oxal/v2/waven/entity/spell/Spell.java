package fr.oxal.v2.waven.entity.spell;

import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.NamedWavenEntity;

public class Spell extends NamedWavenEntity {

    public static final String PATH_SPELL = Wavenpedia.jsonPath + "SpellDefinition/";

    public Spell(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_SPELL;
    }

    @Override
    public String getNameDictionnaire() {
        return SPELL;
    }

    @Override
    public int compareTo(WavenEntity wavenEntity) {
        return 0;
    }
}
