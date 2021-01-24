package fr.oxal.v2.mongo.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.List;

public class MongoUtils {

    public static BasicDBObject toMongoObject(JsonObject json){
        BasicDBObject mongo = new BasicDBObject();
        json.entrySet()
                .stream()
                .forEach(entry -> {
                    if (entry.getValue().isJsonPrimitive()){
                        mongo.append(entry.getKey(), getPrimitiveObject(entry.getValue().getAsJsonPrimitive()));
                    }else if(entry.getValue().isJsonArray()){
                        mongo.append(entry.getKey(), getListObject(entry.getValue().getAsJsonArray()));
                    }else if (entry.getValue().isJsonObject()){
                        mongo.append(entry.getKey(), toMongoObject(entry.getValue().getAsJsonObject()));
                    }
                });

        return mongo;
    }

    public static Object getPrimitiveObject(JsonPrimitive primi){
        if (primi.isNumber()){
            return primi.getAsDouble();
        }else if (primi.isString()){
            return primi.getAsString();
        }else if (primi.isBoolean()){
            return primi.getAsBoolean();
        }
        return null;
    }

    public static List getListObject(JsonArray array){
        ArrayList list = new ArrayList();
        for (JsonElement e : array){
            if (e.isJsonPrimitive()){
                list.add(getPrimitiveObject(e.getAsJsonPrimitive()));
            }else if(e.isJsonArray()){
                list.add(getListObject(e.getAsJsonArray()));
            }else if (e.isJsonObject()){
                list.add(toMongoObject(e.getAsJsonObject()));
            }
        }
        return list;
    }
}
