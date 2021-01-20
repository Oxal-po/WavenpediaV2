package fr.oxal.v2.waven.utils.jsonArgumentEntity.image;

import fr.oxal.v2.waven.entity.WavenInterface;

import java.io.FileInputStream;
import java.util.Optional;

public interface WithImage extends WavenInterface {

    default Optional<FileInputStream> getImage(WavenInterface w){
        return Optional.empty();
    }

    default String getFileName(WavenInterface w){
        if (w.isSpell()){

        }

        return null;
    }
}
