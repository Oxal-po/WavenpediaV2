package fr.oxal.v2.waven.entity.pvm.resource.currency;

public class Currency {

    private int currencyType, amount;

    public final static int GOLD = 0;

    public Currency() {
    }

    public int getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isGold(){
        return currencyType == GOLD;
    }
}
