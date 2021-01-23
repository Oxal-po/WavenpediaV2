package fr.oxal.v2.waven.utils.jsonArgumentEntity.image.logo;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.WavenInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

public interface WithLogo extends WavenInterface {
    Optional<FileInputStream> getLogo();

    default Optional<FileInputStream> getLogo(WavenInterface w){
        File f = new File(Wavenpedia.imagePath + w.getClass().getSimpleName().toLowerCase() + "/" + w.asWavenEntity().getId() + "-logo.png");
        if (f.exists()){
            try {
                return Optional.of(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.err.println("error logo introuvable : " + w.getClass().getSimpleName().toLowerCase() + " : " + w.asWavenEntity().getDisplayName() + " : " + f);
        return Optional.empty();
    }
}
