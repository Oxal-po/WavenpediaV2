package fr.oxal.v2.waven.entity.pvm.resource.astre;

import com.google.gson.JsonArray;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.MerchantCost;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.rarity.WithRarity;

import java.util.Optional;

public class RingResourceDefinition extends WavenEntity implements MerchantCost, WithRarity {

    public static final String RING_RESOURCE_PATH = Wavenpedia.jsonPath + "RingResourceDefinition/";

    public RingResourceDefinition(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return RING_RESOURCE_PATH;
    }

    @Override
    public Optional<JsonArray> getMerchantCost() {
        return getMerchantCost(getJsonRepresentation());
    }

    @Override
    public Optional<Integer> getRarity() {
        return getRarity(getJsonRepresentation());
    }

    @Override
    public int compareTo(WavenEntity wavenEntity) {
        return 0;
    }
}
