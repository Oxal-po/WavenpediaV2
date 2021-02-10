package fr.oxal.v2.waven.utils.updateGauge;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.waven.entity.pvm.skill.Skill;
import fr.oxal.v2.waven.element.WavenElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface WithCost extends UpdateGauge {

    String COSTS = "costs";
    String COST = "cost";

    Optional<JsonArray> getArrayCosts();

    default Optional<JsonArray> getArrayCosts(JsonObject j){
        return Optional.of(j.has(COSTS) ? j.get(COSTS).getAsJsonArray() : j.get(COST).getAsJsonArray());
    }

    default List<WavenElement> getCosts(){
        return getElements(getArrayCosts());
    }

    default List<WavenElement> getCostModifier(){
        if (isWithSkills()){
            ArrayList<WavenElement> list = new ArrayList<>();
            for (Skill s: asWithSkills().getSkills()){
                for (WavenElement e : getCosts()){
                    Optional<WavenElement> o = WavenElement.getModifierElement(s.getParsedDescription(), e);
                    if (o.isPresent()){
                        list.add(o.get());
                    }
                }
            }

            if (!list.isEmpty()){
                return list;
            }

        }
        return getCosts();
    }
}

