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

    List<Integer> getIdSpells();
    List<Spell> getSpells();

    default List<Integer> getIdSpells(JsonObject j){

        ArrayList<Integer> l = new ArrayList<>();

        if (j.has(SPELLS)){
            for (JsonElement e : j.get(SPELLS).getAsJsonArray()){
                if (e.isJsonPrimitive()){
                    l.add(e.getAsInt());
                }
            }
        }
        getWavenRef().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonObject() && e.getAsJsonObject().get(TYPE).getAsInt() == SPELL_ID_TYPE
                        && !l.contains(e.getAsJsonObject().get(ID).getAsDouble())){
                    l.add(e.getAsJsonObject().get(ID).getAsInt());
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

    default void addSpell(int spell){
        getIdSpells().add(spell);
    }

    default void addSpell(Spell spell){
        getIdSpells().add(spell.getId());
    }

    default Spell getSpell(int i){
        return getSpells().get(i);
    }
}