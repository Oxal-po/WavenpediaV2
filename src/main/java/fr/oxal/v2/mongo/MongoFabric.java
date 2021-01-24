package fr.oxal.v2.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import fr.oxal.v2.waven.utils.jsonCreator.Jsoneable;

import java.net.UnknownHostException;

public final class MongoFabric {

    public static MongoClient mongoClient;
    public static DB database;
    public static final String DATABASE_NAME = "Wavenpedia";

    public MongoFabric() {
        if (mongoClient == null){
            try {
                mongoClient = new MongoClient();
                database = mongoClient.getDB(DATABASE_NAME);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    public static DBCollection getColection(String name){
        return database.getCollection(name);
    }

    public static void insert(BasicDBObject obj, DBCollection col){
        col.insert(obj);
    }

    public static void insert(Object o){
        if (o instanceof Jsoneable){
            insert(((Jsoneable) o).transformToMongo(), getColection(o.getClass().getSimpleName()));
        }

        System.out.println("error insert MongoFabric : l'object donné n'est pas jsoneable : " + o.getClass().getSimpleName());
    }

}
