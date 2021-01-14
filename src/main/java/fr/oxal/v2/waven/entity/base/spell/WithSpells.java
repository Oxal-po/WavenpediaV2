package fr.oxal.v2.waven.entity.base.spell;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.WithRefEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithSpells extends WithRefEntity {

    String SPELLS = "spells";
    String ID = "id";
    String TYPE = "type";
    int SPELL_ID_TYPE = 25;

    List<Double> getIdSpells();
    List<Spell> getSpells();

    default List<Double> getIdSpells(JsonObject j){

        ArrayList<Double> l = new ArrayList<>();
        ArrayList<Double> g = new Gson().fromJson(j.get(SPELLS), ArrayList.class);
        if (g != null){
            l.addAll(g);
        }
        getWavenRef().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonObject() && e.getAsJsonObject().get(TYPE).getAsInt() == SPELL_ID_TYPE
                        && !l.contains(e.getAsJsonObject().get(ID).getAsDouble())){
                    l.add(e.getAsJsonObject().get(ID).getAsDouble());
                }
            }
        });

        return l;
    }

    default List<Spell> getSpells(JsonObject j){
        ArrayList<Spell> l = new ArrayList<>();
        for (double d : getIdSpells(j)){
            l.add(new Spell((int) d));
        }

        return l;
    }

    default boolean haveSpell(){
        return !getSpells().isEmpty();
    }

    default boolean haveOneSpell(){
        return getSpells().size() == 1;
    }

    default Optional<Spell> getUniqueSpell(){
        if (haveOneSpell()){
            return Optional.of(getSpell(0));
        }else {
            return Optional.empty();
        }
    }

    default void addSpell(double spell){
        getIdSpells().add(spell);
    }

    default void addSpell(Spell spell){
        getIdSpells().add((double) spell.getId());
    }

    default Spell getSpell(int i){
        return getSpells().get(i);
    }
}