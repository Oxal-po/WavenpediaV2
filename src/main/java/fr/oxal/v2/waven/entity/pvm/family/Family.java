package fr.oxal.v2.waven.entity.pvm.family;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;



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
    public int compareTo(WavenEntity wavenEntity) {
        return 0;
    }
}
