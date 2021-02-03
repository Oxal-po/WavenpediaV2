package fr.oxal.v2.waven.entity.pvm.fragment;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.rarity.WithRarity;

import java.util.Optional;

public class Fragment extends NamedWavenEntity implements WithRarity {

    public static final String PATH_FRAG = Wavenpedia.jsonPath + "FragmentDefinition/";

    public Fragment(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_FRAG;
    }

    @Override
    public String getNameDictionnaire() {
        return RESOURCES;
    }

    @Override
    public Optional<Integer> getRarity() {
        return getRarity(getJsonRepresentation());
    }
}
