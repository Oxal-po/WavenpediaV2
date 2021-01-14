package fr.oxal.v2.waven.utils.collections;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WavenEntities {
    
    public static <T extends WavenEntity> List<T> getAll(Class<T> c){
        ArrayList<T> list = new ArrayList<>();
        construct(c, WavenEntity.NOT_ENTITY)
                .ifPresent(e -> {
                    File folder = new File(e.getPathFolder());
                    if (folder.isDirectory()){
                        for (File file : folder.listFiles()){
                            construct(c, Integer.parseInt(file.getName().split("\\.")[0])).ifPresent(list::add);
                        }
                    }
                });

        return list;
    }

    public static <T extends WavenEntity> List<T> getAll(Class<T> c, Predicate<T> p){
        return getAll(c).stream().filter(p).collect(Collectors.toList());
    }

    public static <T extends WavenEntity> List<T> getAllByName(Class<T> c, String name){
        return Wavenpedia.classedMappedEntity.get(c)
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().toUpperCase().contains(name.toUpperCase()))
                .map(entry -> construct(c, entry.getValue()))
                .filter(op -> op.isPresent())
                .map(op -> op.get())
                .collect(Collectors.toList());
    }


    public static <T extends WavenEntity> List<T> getAllByName(Class<T> c, String name, Predicate<T> supplier){
        return getAllByName(c, name)
                .stream()
                .filter(supplier)
                .collect(Collectors.toList());
    }

    public static <T extends WavenEntity> Optional<T> getOneByName(Class<T> c, String name){
        List<T> list = getAllByName(c, name);

        if (list.size() == 1){
            return Optional.of(list.get(0));
        }

        return Optional.empty();
    }

    public static <T extends WavenEntity> Optional<T> getOneByName(Class<T> c, String name, Predicate<T> predicate){
        List<T> list = getAllByName(c, name, predicate);

        if (list.size() == 1){
            return Optional.of(list.get(0));
        }

        return Optional.empty();
    }

    public static <T> Optional<T> construct(Class<T> c, int id){
        try {
            Constructor constructor = c.getConstructor(int.class);
            if (WavenEntity.fileExist(id, c) || id == WavenEntity.NOT_ENTITY){
                T t = (T) constructor.newInstance(id);
                return Optional.of(t);
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
