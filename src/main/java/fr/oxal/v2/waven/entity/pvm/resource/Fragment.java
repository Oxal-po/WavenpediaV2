package fr.oxal.v2.waven.entity.pvm.resource;

import fr.oxal.v2.Wavenpedia;

public class Fragment extends Resource {

    public static final String PATH_FRAG = Wavenpedia.jsonPath + "FragmentDefinition/";

    public Fragment(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_FRAG;
    }
}
