package fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.util.Optional;

public interface MerchantCost extends WavenInterface {

    String MERCHANT_COST = "merchantCosts";
    String CURRENCY_TYPE = "currencyType";
    String AMOUNT = "amount";

    Optional<JsonArray> getMerchantCost();


    default Optional<JsonArray> getMerchantCost(JsonObject jsonObject){
        return Optional.of((JsonArray) jsonObject.get(MERCHANT_COST));
    }

    default int getCurrencyType(JsonObject j){
        return j.get(CURRENCY_TYPE).getAsInt();
    }

    default int getCurrencyAmount(JsonObject j){
        return j.get(AMOUNT).getAsInt();
    }
}
