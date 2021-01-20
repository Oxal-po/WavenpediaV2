package fr.oxal.v2.waven.entity.base.StatEntity.mechanism;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.base.StatEntity.StatEntity;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.FamiliesEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.PrecomputedEntity;
import fr.oxal.v2.waven.utils.stat.WithShield;

import java.util.List;
import java.util.Optional;

public class ObjectMechanism extends StatEntity implements WithShield, PrecomputedEntity, FamiliesEntity, WithSpells, WithEffect {

    private final static String PATH_MECA = Wavenpedia.jsonPath + "ObjectMechanismDefinition/";

    public ObjectMechanism(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH_MECA;
    }

    @Override
    public String getNameDictionnaire() {
        return MECHANISM;
    }

    @Override
    public Optional<Double> getShield(int level) {
        return getShield(level, getJsonRepresentation());
    }

    @Override
    public List<Double> getIdSpells() {
        return getIdSpells(getJsonRepresentation());
    }

    @Override
    public List<Spell> getSpells() {
        return getSpells(getJsonRepresentation());
    }

    @Override
    public Optional<JsonObject> getDetails() {
        return getDetails(getJsonRepresentation());
    }

    @Override
    public Optional<JsonArray> getFamilies() {
        return getFamilies(getJsonRepresentation());
    }
}
