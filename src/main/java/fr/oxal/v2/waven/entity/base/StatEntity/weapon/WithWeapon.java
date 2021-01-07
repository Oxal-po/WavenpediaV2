package fr.oxal.v2.waven.entity.base.StatEntity.weapon;

import java.util.ArrayList;
import java.util.List;

public interface WithWeapon {

    List<Double> getWeaponsId();

    default boolean haveWeapon(){
        return getWeapons().size() > 0;
    }

    default List<Weapon> getWeapons(){
       ArrayList<Weapon> l = new ArrayList<>();
       for (Double d : getWeaponsId()){
           if (Weapon.fileExist(d.intValue(), Weapon.PATH_WEAPON)){
               l.add(new Weapon(d.intValue()));
           }else{
               System.err.println(String.format("le fichier %d.json n'es pas trouver dans le dossier %s", d.intValue(), Weapon.PATH_WEAPON));
           }
       }

       return l;
    }
}
