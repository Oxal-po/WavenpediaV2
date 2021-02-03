package fr.oxal.v2.waven.entity;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.base.Companion;
import fr.oxal.v2.waven.entity.base.StatEntity.WithStatEntities;
import fr.oxal.v2.waven.entity.base.StatEntity.floorMechanism.FloorMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.mechanism.ObjectMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.summoning.Summoning;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.pvm.equipment.ring.Ring;
import fr.oxal.v2.waven.entity.pvm.skill.WithSkills;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.WithElement;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithCastTarget;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithFilters;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithSelector;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.WithImage;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.logo.WithLogo;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.skin.WithSkin;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.PrecomputedEntity;

public interface WavenInterface {


    default boolean isNamedEntity(){
        return this instanceof NamedWavenEntity;
    }

    default NamedWavenEntity asNamedEntity(){
        return (NamedWavenEntity) this;
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

    default boolean isSpell(){
        return this instanceof Spell;
    }

    default Spell asSpell(){
        return (Spell) this;
    }

    default boolean isWithSkills(){
        return this instanceof WithSkills;
    }

    default WithSkills asWithSkills(){
        return (WithSkills) this;
    }

    default boolean isWithElements(){
        return this instanceof WithElement;
    }

    default WithElement asWithElements(){
        return (WithElement) this;
    }

    default boolean isWithImage(){
        return this instanceof WithImage;
    }

    default WithImage asWithImage(){
        return (WithImage) this;
    }

    default boolean isWithLogo(){
        return this instanceof WithLogo;
    }

    default WithLogo asWithLogo(){
        return (WithLogo) this;
    }

    default boolean isWithSkins(){
        return this instanceof WithSkin;
    }

    default WithSkin asWithSkins(){
        return (WithSkin) this;
    }

    default boolean isWithEffect(){
        return this instanceof WithEffect;
    }

    default WithEffect asWithEffects(){
        return (WithEffect) this;
    }

    default boolean isCompanion(){
        return this instanceof Companion;
    }

    default Companion asCompanion(){
        return (Companion) this;
    }

    default boolean isWeapon(){
        return this instanceof Weapon;
    }

    default Weapon asWeapon(){
        return (Weapon) this;
    }

    default boolean isSummoning(){
        return this instanceof Summoning;
    }

    default Summoning asSummoning(){
        return (Summoning) this;
    }

    default boolean isObjectMechanism(){
        return this instanceof ObjectMechanism;
    }

    default ObjectMechanism asObjectMechanism(){
        return (ObjectMechanism) this;
    }

    default boolean isFloorMechanism(){
        return this instanceof FloorMechanism;
    }

    default FloorMechanism asFloorMechanism(){
        return (FloorMechanism) this;
    }

    default boolean isWithStatEntities(){
        return this instanceof WithStatEntities;
    }

    default WithStatEntities asWithStatEntities(){
        return (WithStatEntities) this;
    }
}
