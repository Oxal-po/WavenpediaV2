package fr.oxal.v2.waven.utils.jsonCreator;

import com.google.gson.*;
import com.mongodb.BasicDBObject;
import fr.oxal.v2.mongo.utils.MongoUtils;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.element.WavenElements;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.element.WavenElement;
import fr.oxal.v2.waven.utils.stat.*;
import fr.oxal.v2.waven.utils.updateGauge.WithCost;
import fr.oxal.v2.waven.utils.updateGauge.WithGains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public interface Jsoneable {

    String NAME_JSON = "name";
    String DESCRI_JSON = "description";
    String STAT_JSON = "stat";
    String LIFE_JSON = "life";
    String ATK_JSON = "atk";
    String PM_JSON = "pm";
    String MECA_LIFE_JSON = "pm";
    String COSTS_JSON = "costs";
    String GAINS_JSON = "gains";
    String ELEMENT_JSON = "element";

    JsonObject transformToJson();

    default BasicDBObject transformToMongo(){
        return MongoUtils.toMongoObject(transformToJson());
    }

    static JsonArray transform(Collection collection){
        JsonArray array = new JsonArray();
        for (Object t : collection){
            array.add(((Jsoneable)t).transformToJson());
        }

        return array;
    }

    default JsonObject defaultJson(WavenEntity o){
        JsonObject json = new JsonObject();
        json.add("type", new JsonPrimitive(o.getClass().getSimpleName()));
        json.add("id", new JsonPrimitive(o.getId()));

        return json;
    }

    static JsonArray toJsonArray(Collection<Integer> c){
        JsonArray a = new JsonArray();
        for (int o : c){
            a.add(new JsonPrimitive(o));
        }
        return a;
    }

    static JsonObject statJson(WithStat s){
        JsonObject json = new JsonObject();

        if (s instanceof WithAtk) {
            json.add(ATK_JSON, new JsonPrimitive(((WithAtk) s).getAtk(1).get()));
        }
        if (s instanceof WithLife) {
            json.add(LIFE_JSON, new JsonPrimitive(((WithLife) s).getLife(1).get()));
        }
        if (s instanceof WithPm) {
            json.add(PM_JSON, new JsonPrimitive(((WithPm) s).getPm(1).get()));
        }
        if (s instanceof WithShield) {
            json.add(MECA_LIFE_JSON, new JsonPrimitive(((WithShield) s).getShield(1).get()));
        }

        return json;
    }

    static JsonObject costJson(WithCost s){
        JsonObject json = new JsonObject();

        for (WavenElement e : WavenElements.getAllElement()){
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(0));
        }

        for (WavenElement e : s.getCosts()){
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(e.getValue()));
        }

        return json;
    }

    static JsonObject gainJson(WithGains s){
        JsonObject json = new JsonObject();

        for (WavenElement e : WavenElements.getAllGauge()){
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(0));
        }

        for (WavenElement e : s.getGains()){
            json.add(e.getGlobalParsedName(0), new JsonPrimitive(e.getValue()));
        }
        return json;
    }

    static void saveJson(Class<? extends Jsoneable> classe, String out){
        JsonArray array = transform(WavenEntities.getAll((Class<? extends WavenEntity>) classe, a -> a.isAvailable()));

        File f = new File(out);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            writer.write(g.toJson(array));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
