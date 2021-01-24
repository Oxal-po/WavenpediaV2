package fr.oxal.v2.waven;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public abstract class WavenEntity  implements Comparable<WavenEntity>{

    private int id;
    private String m_displayName;
    private int m_availability;
    private JsonObject m_jsonRepresentation;


    public static final int NOT_ENTITY = -1;
    public static final JsonParser parser = new JsonParser();
    public static final Gson gson = new Gson();

    public WavenEntity(int id) {
        if (id != NOT_ENTITY){
            this.id = id;
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(getPathFolder() + id + ".json")), StandardCharsets.UTF_8));
                JsonObject jsonObject = (JsonObject) parser.parse(br);
                clone(gson.fromJson(jsonObject, getClass()));
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean fileExist(int id, String path){
        File f = new File(path + id + ".json");
        return f.exists();
    }

    public static boolean fileExist(int id, Class c){
        try {
            Constructor constructor = c.getConstructor(int.class);
            return WavenEntity.fileExist(id, (String) c.getMethod("getPathFolder").invoke(constructor.newInstance(WavenEntity.NOT_ENTITY)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public abstract String getPathFolder();

    public void clone(WavenEntity entity){
        m_displayName = entity.m_displayName;
        m_availability = entity.m_availability;
        m_jsonRepresentation = entity.m_jsonRepresentation;
    }

    public boolean isEntity(){
        return id != NOT_ENTITY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return m_displayName;
    }

    public void setDisplayName(String m_displayName) {
        this.m_displayName = m_displayName;
    }

    public boolean isAvailable() {
        return m_availability == 3;
    }

    public void setAvailable(int m_availability) {
        this.m_availability = m_availability;
    }

    public JsonObject getJsonRepresentation() {
        return m_jsonRepresentation;
    }

    public void setJsonRepresentation(JsonObject m_jsonRepresentation) {
        this.m_jsonRepresentation = m_jsonRepresentation;
    }
}
