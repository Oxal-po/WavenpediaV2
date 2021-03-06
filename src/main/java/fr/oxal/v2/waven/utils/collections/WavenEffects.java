package fr.oxal.v2.waven.utils.collections;

import fr.oxal.v2.utils.text.TextUtils;
import fr.oxal.v2.waven.effect.WavenEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WavenEffects {

    public static List<String> getAllNameEffect(){
        ArrayList<String> list = new ArrayList<>();
        WavenEffect.getKeyWordFile()
                .entrySet()
                .forEach(entry -> list.add(entry.getKey()));
        return list;
    }

    public static List<WavenEffect> getAllEffect(){
        ArrayList<WavenEffect> list = new ArrayList<>();
        WavenEffect.getKeyWordFile()
                .entrySet()
                .forEach(entry -> list.add(new WavenEffect(entry.getKey())));
        return list;
    }

    public static List<WavenEffect> getAllEffect(Predicate<WavenEffect> predicate){
        return getAllEffect()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static List<WavenEffect> getAllEffectByName(String name){
        return getAllEffectByName(name, a -> true);
    }

    public static List<WavenEffect> getAllEffectByName(String name, Predicate<WavenEffect> predicate){
        return getAllEffect(predicate)
                .stream()
                .filter(a ->
                        a.getNameId() != 0 && TextUtils.normalize(a.getName()).toLowerCase().contains(TextUtils.normalize(name).toLowerCase())
                )
                .collect(Collectors.toList());
    }

    public static Optional<WavenEffect> getOneEffectByName(String name){
        return getOneEffectByName(name, a -> true);
    }

    public static Optional<WavenEffect> getOneEffectByName(String name, Predicate<WavenEffect> predicate){
        List<WavenEffect> l = getAllEffectByName(name, predicate);
        if (l.size() == 1) {
            return Optional.of(l.get(0));
        } else if (l.stream().anyMatch(a -> TextUtils.normalize(a.getName().toLowerCase()).equals(TextUtils.normalize(name.toLowerCase())))) {
            return Optional.of(
                    l.stream()
                            .filter(a -> TextUtils.normalize(a.getName().toLowerCase()).equals(TextUtils.normalize(name.toLowerCase())))
                            .collect(Collectors.toList())
                            .get(0)
            );
        }
        return Optional.empty();
    }
}
