package fr.oxal.v2.waven.entity;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.effect.WithEffect;
import fr.oxal.v2.waven.entity.base.Companion;
import fr.oxal.v2.waven.entity.base.StatEntity.WithStatEntities;
import fr.oxal.v2.waven.entity.base.StatEntity.floorMechanism.FloorMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.mechanism.ObjectMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.summoning.Summoning;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.WithWeapon;
import fr.oxal.v2.waven.entity.base.god.WithGods;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.base.spell.WithSpells;
import fr.oxal.v2.waven.entity.pvm.equipment.ring.RingEquipment;
import fr.oxal.v2.waven.entity.pvm.skill.*;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithCastTarget;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithFilters;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.castTarget.WithSelector;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.EquipeableEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.FamiliesEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.WithElement;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.WithImage;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.logo.WithLogo;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.skin.WithSkin;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.PrecomputedEntity;
import fr.oxal.v2.waven.utils.jsonCreator.Jsoneable;
import fr.oxal.v2.waven.utils.stat.*;
import fr.oxal.v2.waven.utils.updateGauge.WithCost;
import fr.oxal.v2.waven.utils.updateGauge.WithGains;

public interface WavenInterface extends Jsoneable {

    @Override
    default JsonObject transformToJson() {
        return Jsoneable.toJson(this);
    }

    default boolean isNamedWavenEntity() {
        return this instanceof NamedWavenEntity;
    }

    default NamedWavenEntity asNamedWavenEntity() {
        return (NamedWavenEntity) this;
    }

    default boolean isNamedEntity() {
        return this instanceof NamedEntity;
    }

    default NamedEntity asNamedEntity(){
        return (NamedEntity) this;
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

    default WithSelector asWithSelector() {
        return (WithSelector) this;
    }

    default boolean isRing() {
        return this instanceof RingEquipment;
    }

    default RingEquipment asRing() {
        return (RingEquipment) this;
    }

    default boolean isSpell() {
        return this instanceof Spell;
    }

    default Spell asSpell() {
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

    default boolean isWithStatEntities() {
        return this instanceof WithStatEntities;
    }

    default WithStatEntities asWithStatEntities() {
        return (WithStatEntities) this;
    }

    default boolean isWithSpells() {
        return this instanceof WithSpells;
    }

    default WithSpells asWithSpells() {
        return (WithSpells) this;
    }

    default boolean isWithGods() {
        return this instanceof WithGods;
    }

    default WithGods asWithGods() {
        return (WithGods) this;
    }

    default boolean isFamiliesEntity() {
        return this instanceof FamiliesEntity;
    }

    default FamiliesEntity asFamiliesEntity() {
        return (FamiliesEntity) this;
    }

    default boolean isSkill() {
        return this instanceof Skill;
    }

    default Skill asSkill() {
        return (Skill) this;
    }

    default boolean isWithAtk() {
        return this instanceof WithAtk;
    }

    default WithAtk asWithAtk() {
        return (WithAtk) this;
    }

    default boolean isWithPm() {
        return this instanceof WithPm;
    }

    default WithPm asWithPm() {
        return (WithPm) this;
    }

    default boolean isWithLife() {
        return this instanceof WithLife;
    }

    default WithLife asWithLife() {
        return (WithLife) this;
    }

    default boolean isWithCritical() {
        return this instanceof WithCritical;
    }

    default WithCritical asWithCritical() {
        return (WithCritical) this;
    }

    default boolean isWithShield() {
        return this instanceof WithShield;
    }

    default WithShield asWithShield() {
        return (WithShield) this;
    }

    default boolean isWithStat() {
        return this instanceof WithStat;
    }

    default WithStat asWithStat() {
        return (WithStat) this;
    }

    default boolean isWithGains() {
        return this instanceof WithGains;
    }

    default WithGains asWithGains() {
        return (WithGains) this;
    }

    default boolean isWithCost() {
        return this instanceof WithCost;
    }

    default WithCost asWithCost() {
        return (WithCost) this;
    }

    default boolean isEquipeableEntity() {
        return this instanceof EquipeableEntity;
    }

    default EquipeableEntity asEquipeableEntity() {
        return (EquipeableEntity) this;
    }

    default boolean isWithWeapons() {
        return this instanceof WithWeapon;
    }

    default WithWeapon asWithWeapons() {
        return (WithWeapon) this;
    }

    default boolean isWithElementarySkills() {
        return this instanceof WithElementarySkills;
    }

    default WithElementarySkills asWithElementarySkills() {
        return (WithElementarySkills) this;
    }

    default boolean isWithSpecificSkills() {
        return this instanceof WithSpecificSkills;
    }

    default WithSpecificSkills asWithSpecificSkills() {
        return (WithSpecificSkills) this;
    }

    default boolean isWithUnlockableSkill() {
        return this instanceof WithUnlockableSkill;
    }

    default WithUnlockableSkill asWithUnlockableSkill() {
        return (WithUnlockableSkill) this;
    }
}
