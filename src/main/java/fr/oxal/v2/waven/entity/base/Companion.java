package fr.oxal.v2.waven.entity.base;

import com.google.gson.JsonArray;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.pvm.skill.WithSkills;

import java.util.Optional;

public class Companion extends NamedWavenEntity implements WithSkills {

    private final static String PATH_COMPANION = Wavenpedia.jsonPath + "CompanionDefinition/";

    public Companion(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_COMPANION;
    }

    @Override
    public String getNameDictionnaire() {
        return COMPANION;
    }

    @Override
    public Optional<JsonArray> getJsonArraySkills() {
        return getJsonArraySkills(getJsonRepresentation());
    }
}
