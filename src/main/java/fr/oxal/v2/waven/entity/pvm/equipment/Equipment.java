package fr.oxal.v2.waven.entity.pvm.equipment;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.utils.dictionary.DictionaryFabric;

public abstract class Equipment extends NamedWavenEntity {

    public Equipment(int id) {
        super(id);
    }

    @Override
    public String getNameDictionnaire() {
        return EQUIPMENT;
    }
}
