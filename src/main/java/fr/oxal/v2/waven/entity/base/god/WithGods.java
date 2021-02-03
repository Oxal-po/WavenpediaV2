package fr.oxal.v2.waven.entity.base.god;

import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.FamiliesEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface WithGods extends FamiliesEntity {

    default ArrayList<God> getGods(){
        ArrayList<God> gods = new ArrayList<>();

        for (Integer i : getFamilyIds()){
            if (God.fileExist(i, God.class)){
                gods.add(new God(i));
            }
        }

        return gods;
    }

    default boolean haveGod(){
        return getFamilyIds().size() > 1;
    }

    default boolean hasGod(God g){
        return hasFamily(g);
    }
}
