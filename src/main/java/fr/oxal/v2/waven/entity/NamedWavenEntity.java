package fr.oxal.v2.waven.entity;

import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class NamedWavenEntity extends WavenEntity implements NamedEntity {

    private static JsonObject dictionary;

    public NamedWavenEntity(int id) {
        super(id);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Wavenpedia.dictionaryPath + getPathFolder())), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Reader reader = br) {
            dictionary = (JsonObject) WavenEntity.parser.parse(reader);
        }catch (Exception e){
            dictionary = new JsonObject();
        }
    }

    @Override
    public String getText(long id) {
        return dictionary.get(id + "").getAsString();
    }
}
