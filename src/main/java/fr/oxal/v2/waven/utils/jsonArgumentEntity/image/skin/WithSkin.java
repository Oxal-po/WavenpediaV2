package fr.oxal.v2.waven.utils.jsonArgumentEntity.image.skin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.utils.text.TextUtils;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.image.WithImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Optional;

public interface WithSkin extends WavenInterface {

    String DEFAULT_SKIN = "defaultSkin";
    String SKINS = "skins";
    String SKIN_FORMAT = "%d#%d.png";

    Optional<Integer> getDefaultSkin();

    default Optional<Integer> getDefaultSkin(JsonObject j){
        return Optional.of(j.has(DEFAULT_SKIN) ? j.get(DEFAULT_SKIN).getAsInt() : null);
    }

    Optional<JsonArray> getSkins();

    default Optional<JsonArray> getSkins(JsonObject j){
        return Optional.of((JsonArray) j.get(SKINS));
    }

    ArrayList<FileInputStream> getFileSkins();

    default ArrayList<FileInputStream> getFileSkins(WavenEntity w){
        ArrayList<FileInputStream> files = new ArrayList<>();

        getSkins().ifPresent(a -> {
            for (JsonElement e : a){
                if (e.isJsonPrimitive()){
                    String name = Wavenpedia.imagePath + w.getClass().getSimpleName().toLowerCase() + "/" + String.format(SKIN_FORMAT, w.getId(), e.getAsInt());
                    File f = new File(name);

                    if (f.exists()){
                        try {
                            files.add(new FileInputStream(f));
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }else{
                        System.err.println("error Skin introuvable : " + w.getClass().getSimpleName().toLowerCase() + " : skin id : " + e.getAsInt() + " : " + w.getDisplayName() + " : " + f);
                    }
                }
            }
        });
        return files;
    }

    default Optional<FileInputStream> getInputStreamFileByName(String name){
        File f = new File(name);
        String[] nameSplit = name.split("-");
        if (f.exists()){
            try {
                return Optional.of(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(name.contains(" - QUETE")){
            return getInputStreamFileByName(name.replace("QUETE", "PNJ"));
        }else if(nameSplit.length == 4){
            return getInputStreamFileByName(nameSplit[0] + "-" + nameSplit[1] + "-" + nameSplit[3]);
        }


        System.err.println("error : le nom du fichier n'existe pas : " + f);
        return Optional.empty();
    }

    default String getIdSkin(int id){
        String str = "0000";
        for (int i = 10 ; i < 10000; i *= 10){
            if (id < i){

            }else{
                str = str.substring(1);
            }
        }

        return str + id;
    }
}
