package fr.oxal.v2;

import fr.oxal.v2.mongo.MongoFabric;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.effect.FloatingCounterEffect;
import fr.oxal.v2.waven.effect.PropertyEffect;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.base.Companion;
import fr.oxal.v2.waven.entity.base.StatEntity.floorMechanism.FloorMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.mechanism.ObjectMechanism;
import fr.oxal.v2.waven.entity.base.StatEntity.summoning.Summoning;
import fr.oxal.v2.waven.entity.base.StatEntity.weapon.Weapon;
import fr.oxal.v2.waven.entity.base.god.God;
import fr.oxal.v2.waven.entity.base.spell.Spell;
import fr.oxal.v2.waven.entity.pvm.equipment.gem.Gem;
import fr.oxal.v2.waven.entity.pvm.fragment.Fragment;
import fr.oxal.v2.waven.entity.pvm.skill.Skill;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.precompueted.DynamicedEntity;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Optional;

public class Wavenpedia {

    public static String jsonPath;
    public static String imagePath;
    public static String dictionaryPath;
    public static String keyWordPath;
    public static String constPath;
    public static final MongoFabric mongo = new MongoFabric();

    public static final Class<? extends NamedWavenEntity>[] ALL_NAMED_CLASS = new Class[]{
            Spell.class, God.class, Weapon.class, Companion.class, ObjectMechanism.class, Summoning.class,
            FloorMechanism.class, Skill.class, Gem.class, Fragment.class
    };
    public static final Class<? extends DynamicedEntity>[] ALL_DYNAMICED_CLASS = new Class[]{
            PropertyEffect.class, Spell.class, God.class, Weapon.class, Companion.class, ObjectMechanism.class,
            Summoning.class, FloatingCounterEffect.class , FloorMechanism.class, Skill.class
    };

    public static HashMap<Class<? extends WavenEntity>, HashMap<String, Integer>> classedMappedEntity;

    public static Optional<Integer> getIdEntity(Class c, String name){
        HashMap<String, Integer> h = classedMappedEntity.get(c);
        if (h == null){
            return Optional.empty();
        }
        return Optional.of(h.get(name));
    }

    public static <T> Optional<T> getEntity(Class<T> c, String name){
        try {
            return Optional.of(c.getConstructor(int.class).newInstance(getIdEntity(c, name)));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static void setJsonPath(String path){
        jsonPath = path;
    }

    public static void setImagePath(String imagePath) {
        Wavenpedia.imagePath = imagePath;
    }

    public static void setDictionaryPath(String dictionaryPath) {
        Wavenpedia.dictionaryPath = dictionaryPath;
    }

    public static void setKeyWordPath(String keyWordPath) {
        Wavenpedia.keyWordPath = keyWordPath;
    }

    public static void setConstPath(String constPath) {
        Wavenpedia.constPath = constPath;
    }

    public static MongoFabric getMongo() {
        return mongo;
    }

    public static void start(){
        classedMappedEntity = new HashMap<>();
        for (Class<? extends NamedWavenEntity> c : ALL_NAMED_CLASS){
            HashMap<String, Integer> mappedEntity = new HashMap<>();
            for (NamedWavenEntity a : WavenEntities.getAll(c)){
                mappedEntity.put(a.getName(), a.getId());
            }
            classedMappedEntity.put(c, mappedEntity);
        }
    }

}
