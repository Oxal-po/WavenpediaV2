package fr.oxal.v2.waven.utils.jsonArgumentEntity.image;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.utils.text.TextUtils;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface WithImage extends WavenInterface {

    Optional<FileInputStream> getImage();

    default Optional<FileInputStream> getImage(WavenInterface w){
        File f = new File(Wavenpedia.imagePath + w.getClass().getSimpleName().toLowerCase() + "/" + w.asWavenEntity().getId() + ".png");
        if (f.exists()){
            try {
                return Optional.of(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        System.err.println("error image introuvable : " + w.getClass().getSimpleName().toLowerCase() + " : " + w.asWavenEntity().getDisplayName() + " : " + f);
        return Optional.empty();
    }
}
