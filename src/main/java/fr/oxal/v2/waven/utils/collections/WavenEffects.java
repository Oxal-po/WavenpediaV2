package fr.oxal.v2.waven.utils.collections;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.effect.WavenEffect;

import java.util.ArrayList;

public class WavenEffects {

    public ArrayList<String> getAllNameEffect(){
        ArrayList<String> list = new ArrayList<>();
        WavenEffect.getKeyWordFile()
                .entrySet()
                .forEach(entry -> list.add(entry.getKey()));
        return list;
    }

    public ArrayList<WavenEffect> getAllEffect(){
        ArrayList<WavenEffect> list = new ArrayList<>();
        WavenEffect.getKeyWordFile()
                .entrySet()
                .forEach(entry -> list.add(new WavenEffect(entry.getKey())));
        return list;
    }
}
