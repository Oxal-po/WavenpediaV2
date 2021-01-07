package fr.oxal.v2.waven;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class WavenEntity  implements Comparable<WavenEntity>{

    private int id;
    private String m_displayName;
    private boolean m_availability;
    private JsonObject m_jsonRepresentation;


    public static final int NOT_ENTITY = -1;
    public static final JsonParser parser = new JsonParser();
    public static final Gson gson = new Gson();

    public WavenEntity(int id) {
        this.id = id;
        if (id != NOT_ENTITY){
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

    public abstract String getPathFolder();

    public void clone(WavenEntity entity){
        id = entity.id;
        m_displayName = entity.m_displayName;
        m_availability = entity.m_availability;
        m_jsonRepresentation = entity.m_jsonRepresentation;
    }

    public boolean isEntity(){
        return id != NOT_ENTITY;
    }
}
