package fr.oxal.v2.waven.entity;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.dictionary.DictionaryFabric;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;

import java.util.Comparator;

public abstract class NamedWavenEntity extends WavenEntity implements NamedEntity, DynamicedEntity {

    protected static JsonObject dictionary;
    private long m_i18nNameId, m_i18nDescriptionId;

    public NamedWavenEntity(int id) {
        super(id);
    }

    @Override
    public String getText(long id) {
        return getDictionary().get(id + "").getAsString();
    }

    @Override
    public long getNameId() {
        return m_i18nNameId;
    }

    @Override
    public long getDescriptionId() {
        return m_i18nDescriptionId;
    }

    @Override
    public String getName() {
        return getDictionary().get(getNameId() + "").getAsString();
    }

    @Override
    public String getDescription() {
        return getDictionary().get(getDescriptionId() + "").getAsString();
    }

    @Override
    public void clone(WavenEntity entity) {
        super.clone(entity);
        if (entity instanceof NamedWavenEntity){
            m_i18nNameId = ((NamedWavenEntity) entity).m_i18nNameId;
            m_i18nDescriptionId = ((NamedWavenEntity) entity).m_i18nDescriptionId;
        }
    }

    @Override
    public JsonObject getDictionary() {
        return DictionaryFabric.getDictionary(this);
    }

    @Override
    public int compareTo(WavenInterface wavenEntity) {
        if (!(wavenEntity instanceof NamedWavenEntity)) {
            return Comparator.<Integer>naturalOrder().compare(getId(), wavenEntity.asWavenEntity().getId());
        }
        return getName().compareTo(((NamedWavenEntity) wavenEntity).getName());
    }

    @Override
    public JsonObject getPrecomputeData() {
        return getPrecomputeData(getJsonRepresentation());
    }
}
