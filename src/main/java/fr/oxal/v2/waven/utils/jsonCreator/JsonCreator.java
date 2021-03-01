package fr.oxal.v2.waven.utils.jsonCreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.collections.WavenEntities;

import java.io.File;
import java.io.IOException;

import static fr.oxal.v2.waven.utils.jsonCreator.FileMaker.makeFile;

public final class JsonCreator {

    public static void makeJson(String out, Class<? extends WavenEntity>... classes) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(out);
        file.mkdirs();
        for (Class<? extends WavenEntity> c : classes) {
            JsonArray array = new JsonArray();
            WavenEntities.getAll(c, a -> a.isAvailable()).forEach(a -> array.add(a.transformToJson()));

            try {
                makeFile(gson.toJson(array), out + "/" + c.getSimpleName() + ".json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Wavenpedia.setConstPath("../data7/json/ConstantsDefinition/25.json");
        Wavenpedia.setDictionaryPath("../data7/json/dico/");
        Wavenpedia.setJsonPath("../data7/json/");
        Wavenpedia.setKeyWordPath("../data7/json/KeyWord/");
        Wavenpedia.setImagePath("../data/imagesV4/");
        Wavenpedia.start();
        makeJson("out/json", Wavenpedia.ALL_NAMED_CLASS);
    }
}
