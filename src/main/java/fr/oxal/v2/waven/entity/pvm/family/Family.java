package fr.oxal.v2.waven.entity.pvm.family;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;


public class Family extends WavenEntity {

    public static final String FAMILY_PATH = Wavenpedia.jsonPath + "FamilyDefinition/";

    public Family(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return FAMILY_PATH;
    }

    @Override
    public int compareTo(WavenInterface wavenEntity) {
        return 0;
    }
}
