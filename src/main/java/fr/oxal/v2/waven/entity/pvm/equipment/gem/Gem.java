package fr.oxal.v2.waven.entity.pvm.equipment.gem;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.equipment.Equipment;

import java.util.List;
import java.util.stream.Collectors;

public class Gem extends Equipment implements WithEffect, WithSpells {

    public static final String PATH_GEM = Wavenpedia.jsonPath + "GemEquipmentDefinition/";

    public Gem(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_GEM;
    }

    @Override
    public List<Integer> getIdSpells() {
        return getIdSpells(getJsonRepresentation());
    }

    @Override
    public List<Spell> getSpells() {
        return getSpells(getJsonRepresentation());
    }

    @Override
    public String getParsedDescription(int level, int... option) {
        return super.getParsedDescription(level, option) + " " + getSpells()
                .stream()
                .map(a -> a.getParsedName(level, option))
                .collect(Collectors.joining(", "))
                ;
    }
}
