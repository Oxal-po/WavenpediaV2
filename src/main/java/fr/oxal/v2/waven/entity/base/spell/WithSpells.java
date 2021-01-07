package fr.oxal.v2.waven.entity.base.spell;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithSpells {

    String SPELLS = "spells";

    List<Double> getSpells();

    default List<Double> getSpells(JsonObject j){
        return new Gson().fromJson(j.get(SPELLS), ArrayList.class);
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
        getSpells().add(spell);
    }

    default void addSpell(Spell spell){
        getSpells().add((double) spell.getId());
    }

    default Spell getSpell(int i){
        return new Spell(getSpells().get(i).intValue());
    }
}