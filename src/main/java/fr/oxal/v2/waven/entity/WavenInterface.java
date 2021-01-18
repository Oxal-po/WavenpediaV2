package fr.oxal.v2.waven.entity;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.entity.base.StatEntity.StatEntity;
import fr.oxal.v2.waven.entity.pvm.equipment.ring.Ring;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithCastTarget;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithFilters;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithSelector;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.PrecomputedEntity;

public interface WavenInterface {


    default boolean isNamedEntity(){
        return this instanceof NamedWavenEntity;
    }

    default NamedWavenEntity asNamedEntity(){
        return (NamedWavenEntity) this;
    }

    default boolean isStatEntity(){
        return this instanceof StatEntity;
    }

    default StatEntity asStatEntity(){
        return (StatEntity) this;
    }

    default boolean isWavenEntity(){
        return this instanceof WavenEntity;
    }

    default WavenEntity asWavenEntity(){
        return (WavenEntity) this;
    }

    default boolean isWavenEffect(){
        return this instanceof WavenEffect;
    }

    default WavenEffect asWavenEffect(){
        return (WavenEffect) this;
    }

    default boolean isDynamicedEntity(){
        return this instanceof DynamicedEntity;
    }

    default DynamicedEntity asDynamicedEntity(){
        return (DynamicedEntity) this;
    }

    default boolean isPrecomputedEntity(){
        return this instanceof PrecomputedEntity;
    }

    default PrecomputedEntity asPrecomputedEntity(){
        return (PrecomputedEntity) this;
    }

    default boolean isWithCastTarget(){
        return this instanceof WithCastTarget;
    }

    default WithCastTarget asWithCastTarget(){
        return (WithCastTarget) this;
    }

    default boolean isWithFilters(){
        return this instanceof WithFilters;
    }

    default WithFilters asWithFilters(){
        return (WithFilters) this;
    }

    default boolean isWithSelector(){
        return this instanceof WithSelector;
    }

    default WithSelector asWithSelector(){
        return (WithSelector) this;
    }

    default boolean isRing(){
        return this instanceof Ring;
    }

    default Ring asRing(){
        return (Ring) this;
    }
}
