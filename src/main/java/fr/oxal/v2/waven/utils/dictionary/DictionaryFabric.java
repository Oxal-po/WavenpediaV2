package fr.oxal.v2.waven.utils.dictionary;

import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.NamedWavenEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class DictionaryFabric {

    public static HashMap<Class, JsonObject> dictionaries = new HashMap<>();

    public static JsonObject getDictionary(NamedWavenEntity entity){
        return getDictionary(entity.getClass(), Wavenpedia.dictionaryPath + entity.getNameDictionnaire());
    }

    public static JsonObject getDictionary(Class c, String file){
        if (!dictionaries.containsKey(c)){
            BufferedReader br = null;
            JsonObject j = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), StandardCharsets.UTF_8));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try (Reader reader = br) {
                j = (JsonObject) WavenEntity.parser.parse(reader);
            }catch (Exception e){
                j = new JsonObject();
            }
            dictionaries.put(c, j);
        }

        return dictionaries.get(c);
    }
}
