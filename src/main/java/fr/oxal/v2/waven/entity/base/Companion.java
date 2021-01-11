package fr.oxal.v2.waven.entity.base;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;

public class Companion extends NamedWavenEntity {

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
}
