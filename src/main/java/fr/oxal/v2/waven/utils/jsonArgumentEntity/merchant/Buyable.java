package fr.oxal.v2.waven.utils.jsonArgumentEntity.merchant;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

public interface Buyable extends WavenInterface {

    String MERCHANT = "merchantCosts";

    Optional<JsonArray> getArrayMerchantCost();

    default Optional<JsonArray> getArrayMerchantCost(JsonObject j){
        return Optional.of((JsonArray) j.get(MERCHANT));
    }

    default List<Currency> getMerchantCost(){
        ArrayList<Currency> list = new ArrayList<>();

        getArrayMerchantCost().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonObject()){
                    list.add(Wavenpedia.gson.fromJson(e, Currency.class));
                }
            }
        });

        return list;
    }
}
