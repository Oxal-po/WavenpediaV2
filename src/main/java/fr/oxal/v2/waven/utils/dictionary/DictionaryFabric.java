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
        if (!dictionaries.containsKey(entity.getClass())){
            BufferedReader br = null;
            JsonObject j = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Wavenpedia.dictionaryPath + entity.getNameDictionnaire())), StandardCharsets.UTF_8));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try (Reader reader = br) {
                j = (JsonObject) WavenEntity.parser.parse(reader);
            }catch (Exception e){
                j = new JsonObject();
            }
            dictionaries.put(entity.getClass(), j);
        }

        return dictionaries.get(entity.getClass());
    }
}
