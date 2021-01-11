package fr.oxal.v2.waven.effect;

import java.util.ArrayList;

public class ValuesEffect extends WavenEffect{

    private ArrayList<Double> values;

    public ValuesEffect(String keyWord) {
        super(keyWord);
        values = new ArrayList<>();
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public double getValue(int level){
        if (level - 1 > values.size()){
            return values.get(values.size() - 1);
        }

        return values.get(level - 1);
    }

    public double getValue(){
        return getValue(1);
    }

    public boolean haveUniqueValues(){
        return getValues().size() == 1;
    }
}
