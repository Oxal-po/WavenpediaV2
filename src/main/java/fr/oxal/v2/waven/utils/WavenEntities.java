package fr.oxal.v2.waven.utils;

import fr.oxal.v2.waven.WavenEntity;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WavenEntities {
    
    public static <T extends WavenEntity> List<T> getAll(Class<T> c){
        ArrayList<T> list = new ArrayList<>();
        try {
            Constructor constructor = c.getConstructor(int.class);
            Object o = constructor.newInstance(WavenEntity.NOT_ENTITY);
            Method method = c.getMethod("getPathFolder");
            File folder = new File((String) method.invoke(o));
            if (folder.isDirectory()){
                for (File file : folder.listFiles()){
                    try{
                        list.add((T) constructor.newInstance(Integer.parseInt(file.getName().split("\\.")[0])));
                    }catch (InvocationTargetException e){
                        System.err.println(file);
                        e.printStackTrace();
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static <T extends WavenEntity> List<T> getAll(Class<T> c, Predicate<T> p){
        return getAll(c).stream().filter(p).collect(Collectors.toList());
    }
}
