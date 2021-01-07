package fr.oxal.v2.waven.entity.base.spell;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.god.God;
import fr.oxal.v2.waven.entity.base.god.WithGods;

import java.util.List;

public class Spell extends NamedWavenEntity implements WithGods {

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
    public void clone(WavenEntity entity) {
        super.clone(entity);
    }

    @Override
    public JsonArray getFamilies() {
        return getFamilies(getJsonRepresentation());
    }

    @Override
    public JsonObject getDetails() {
        return getDetails(getJsonRepresentation());
    }
}
