package fr.oxal.v2.waven.entity.pvm.resource;

import com.google.gson.JsonArray;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.pvm.drop.SingleObtainableItemList;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.Dropeable;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm.MerchantCost;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.rarity.WithRarity;

import java.util.List;
import java.util.Optional;

public abstract class Resource extends NamedWavenEntity implements WithRarity, Dropeable, MerchantCost {
    public Resource(int id) {
        super(id);
    }

    @Override
    public String getNameDictionnaire() {
        return RESOURCES;
    }

    @Override
    public Optional<Integer> getRarity() {
        return getRarity(getJsonRepresentation());
    }

    @Override
    public List<SingleObtainableItemList> getDropZone() {
        return getDropZone(this);
    }

    @Override
    public Optional<JsonArray> getMerchantCost() {
        return getMerchantCost(getJsonRepresentation());
    }
}
