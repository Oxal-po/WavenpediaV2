package fr.oxal.v2.waven.utils.jsonCreator;

import com.google.gson.*;
import com.mongodb.BasicDBObject;
import fr.oxal.v2.mongo.utils.MongoUtils;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.element.WavenElement;
import fr.oxal.v2.waven.element.WavenElements;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.stat.WithStat;
import fr.oxal.v2.waven.utils.updateGauge.WithCost;
import fr.oxal.v2.waven.utils.updateGauge.WithGains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public interface Jsoneable {

    String NAME_JSON = "name";
    String DESCRI_JSON = "description";
    String DESCRI_JSON_LEVEL = "description_level";
    String STAT_JSON = "stat";
    String STAT_JSON_LEVEL = "stat_level";
    String LIFE_JSON = "life";
    String ATK_JSON = "atk";
    String PM_JSON = "pm";
    String CRIT_CHANCE_JSON = "crit_chance";
    String CRIT_DAMAGE_JSON = "crit_damage";
    String MECA_LIFE_JSON = "shield";
    String COSTS_JSON = "costs";
    String GAINS_JSON = "gains";
    String ELEMENT_JSON = "element";
    String SKILLS_JSON = "skills";

    JsonObject transformToJson();

    default BasicDBObject transformToMongo() {
        return MongoUtils.toMongoObject(transformToJson());
    }

    static JsonArray toJsonArray(Collection c) {
        JsonArray a = new JsonArray();
        for (Object o : c) {
            if (o instanceof Number) {
                a.add(new JsonPrimitive((Number) o));
            } else if (o instanceof String) {
                a.add(new JsonPrimitive((String) o));
            } else if (o instanceof Boolean) {
                a.add(new JsonPrimitive((Boolean) o));
            }
        }
        return a;
    }

    static JsonElement getStat(WithStat waven) {
        JsonElement e = null;

        if (waven.isWeapon() || waven.isCompanion() || waven.isSummoning() || waven.isObjectMechanism() || waven.isFloorMechanism()) {
            e = getStat(waven, 100);
        }

        return e;
    }

    static JsonElement getStat(WithStat waven, int max) {
        JsonArray array = new JsonArray();

        for (int i = 1; i <= max; i++) {
            JsonObject json = new JsonObject();

            if (waven.isWithAtk()) {
                waven.asWithAtk().getAtk(i).ifPresent(a -> {
                    if (a > 0) {
                        json.add(ATK_JSON, new JsonPrimitive(a));
                    }
                });
            }
            if (waven.isWithLife()) {
                waven.asWithLife().getLife(i).ifPresent(a -> {
                    if (a > 0) {
                        json.add(LIFE_JSON, new JsonPrimitive(a));
                    }
                });
            }
            if (waven.isWithPm()) {
                waven.asWithPm().getPm(i).ifPresent(a -> {
                    if (a > 0) {
                        json.add(PM_JSON, new JsonPrimitive(a));
                    }
                });
            }
            if (waven.isWithShield()) {
                waven.asWithShield().getShield(i).ifPresent(a -> {
                    if (a > 0) {
                        json.add(MECA_LIFE_JSON, new JsonPrimitive(a));
                    }
                });
            }
            if (waven.isWithCritical()) {
                waven.asWithCritical().getCriticalChance(i).ifPresent(a -> {
                    if (a > 0) {
                        json.add(CRIT_CHANCE_JSON, new JsonPrimitive(a));
                    }
                });
                waven.asWithCritical().getCriticalDamage(i).ifPresent(a -> {
                    if (a > 0) {
                        json.add(CRIT_DAMAGE_JSON, new JsonPrimitive(a));
                    }
                });
            }

            array.add(json);
        }

        return array;
    }

    static void saveJson(Class<? extends NamedEntity> c, String out) {
        JsonArray array = new JsonArray();

        for (WavenInterface w : WavenEntities.getAll(c)) {
            array.add(w.transformToJson());
        }

        File f = new File(out);
        f.mkdirs();
        try {
            f = new File(out + c.getSimpleName() + ".json");
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            writer.write(g.toJson(array));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static JsonObject costJson(WithCost s) {
        JsonObject json = new JsonObject();

        for (WavenElement e : WavenElements.getAllElement()) {
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(0));
        }

        for (WavenElement e : s.getCosts()) {
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(e.getValue()));
        }

        return json;
    }

    static JsonObject gainJson(WithGains s) {
        JsonObject json = new JsonObject();

        for (WavenElement e : WavenElements.getAllGauge()) {
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(0));
        }

        for (WavenElement e : s.getGains()) {
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(e.getValue()));
        }
        return json;
    }

    static JsonElement getDescription(NamedEntity waven) {
        JsonElement e = null;
        final int BASIC_MAX_LEVEL = 100;
        final int SPELL_MAX_LEVEL = 50;
        final int SKILL_MAX_LEVEL = 15;
        if (waven.isRing()) {
            e = new JsonArray();
            for (Integer i : waven.asRing().getLevels()) {
                e.getAsJsonArray().add(new JsonPrimitive(waven.getGlobalParsedDescription(i)));
            }
        } else if (waven.isSpell()) {
            e = getDescription(waven, SPELL_MAX_LEVEL);
        } else if (waven.isCompanion() || waven.isSummoning()) {
            e = getDescription(waven, BASIC_MAX_LEVEL);
        } else if (waven.isSkill()) {
            e = getDescription(waven, SKILL_MAX_LEVEL);
        } else if (waven.isWavenEffect()) {
            e = getDescription(waven, 1);
        }

        return e;
    }

    static JsonElement getDescription(NamedEntity waven, int max) {
        JsonElement e = null;
        //je regarde si la description est évolutif (change en fonction des levels)
        try {
            if (!waven.getGlobalParsedDescription(1).equals(waven.getGlobalParsedDescription(max))) {
                e = new JsonArray();
                for (int i = 1; i <= max; i++) {
                    e.getAsJsonArray().add(new JsonPrimitive(waven.getGlobalParsedDescription(i)));
                }
            } else {
                e = new JsonPrimitive(waven.getGlobalParsedDescription(1));
            }
        } catch (Exception ex) {
        }
        return e;
    }

    static JsonObject toJson(WavenInterface waven) {
        JsonObject json = new JsonObject();

        if (waven.isWavenEntity()) {
            json.add("type", new JsonPrimitive(waven.getClass().getSimpleName()));
            json.add("id", new JsonPrimitive(waven.asWavenEntity().getId()));
            json.add("available", new JsonPrimitive(waven.asWavenEntity().isAvailable()));
            json.add("display_name", new JsonPrimitive(waven.asWavenEntity().getDisplayName()));
        }

        if (waven.isEquipeableEntity()) {
            json.add("equipeable", new JsonPrimitive(waven.asEquipeableEntity().isEquipeable()));
        }

        if (waven.isNamedEntity()) {

            json.add("name", new JsonPrimitive(waven.asNamedEntity().getGlobalParsedName(0)));


            if (waven.isWavenEffect()) {
                json.add("key", new JsonPrimitive(waven.asWavenEffect().getKeyWord()));
            }

            JsonElement e = getDescription(waven.asNamedEntity());

            if (e != null && e.isJsonArray() && e.getAsJsonArray().size() > 0) {
                json.add("description_level", e);
            } else if (e != null && e.isJsonPrimitive()) {
                json.add("description", e);
            }
        }

        if (waven.isWithSpells()) {
            json.add("spells", Jsoneable.toJsonArray(waven.asWithSpells().getIdSpells()));
        }

        if (waven.isWithSkills()) {
            json.add("skills", Jsoneable.toJsonArray(waven.asWithSkills().getIdSkills()));
        }

        if (waven.isWithGods()) {
            json.add("gods", Jsoneable.toJsonArray(waven.asWithGods().getIdGods()));
        }

        if (waven.isFamiliesEntity()) {
            json.add("family", Jsoneable.toJsonArray(waven.asFamiliesEntity().getFamilyIdsNoGods()));
        }

        if (waven.isWithEffect()) {
            json.add("effects", Jsoneable.toJsonArray(waven.asWithEffects().getNameEffects()));
        }

        if (waven.isWithStat()) {
            JsonElement e = getDescription(waven.asNamedEntity());

            if (e != null && e.isJsonArray() && e.getAsJsonArray().size() > 0) {
                json.add("stat_level", e);
            } else if (e != null && e.isJsonPrimitive()) {
                json.add("stat", e);
            }
        }

        if (waven.isWithCost()) {
            json.add("costs", Jsoneable.costJson(waven.asWithCost()));
        }

        if (waven.isWithGains()) {
            json.add("gains", Jsoneable.gainJson(waven.asWithGains()));
        }

        if (waven.isWithWeapons()) {
            json.add("weapons", Jsoneable.toJsonArray(waven.asWithWeapons().getWeaponsId()));
        }

        if (waven.isWithElements()) {
            waven.asWithElements().getIdElement().ifPresent(a -> json.add("element", new JsonPrimitive(a)));
        }

        if (waven.isWithStatEntities()) {
            json.add("summonings", toJsonArray(waven.asWithStatEntities().getSummonings().stream().map(a -> a.getId()).collect(Collectors.toList())));
            json.add("companions", toJsonArray(waven.asWithStatEntities().getCompanions().stream().map(a -> a.getId()).collect(Collectors.toList())));
            json.add("floors", toJsonArray(waven.asWithStatEntities().getFloorMechanisms().stream().map(a -> a.getId()).collect(Collectors.toList())));
            json.add("mechanism", toJsonArray(waven.asWithStatEntities().getObjectMechanisms().stream().map(a -> a.getId()).collect(Collectors.toList())));
        }

        if (waven.isRing()) {
            json.add("levels", toJsonArray(waven.asRing().getLevels()));
        }


        return json;
    }

    default JsonObject defaultJson(WavenEntity o) {
        JsonObject json = new JsonObject();
        json.add("type", new JsonPrimitive(o.getClass().getSimpleName()));
        json.add("id", new JsonPrimitive(o.getId()));
        json.add("available", new JsonPrimitive(o.isAvailable()));

        return json;
    }
}
