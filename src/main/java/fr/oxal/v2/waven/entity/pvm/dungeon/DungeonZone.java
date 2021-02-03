package fr.oxal.v2.waven.entity.pvm.dungeon;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.pvm.dungeon.chapter.WithChapters;
import fr.oxal.v2.waven.entity.pvm.family.Family;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.FamiliesEntity;

import java.util.Optional;

public class DungeonZone extends NamedWavenEntity implements WithChapters {

    public static final String PATH = Wavenpedia.jsonPath + "DungeonZoneDefinition/";
    private static final String RANKS = "rangs";
    private static final String MIN_PLAYER = "minPlayers";
    private static final String MAX_PLAYER = "maxPlayers";
    private static final String RELATED_FAMILY = "relatedFamily";

    public DungeonZone(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return null;
    }

    @Override
    public String getNameDictionnaire() {
        return ZONES;
    }

    @Override
    public String getDescription() {
        return NamedEntity.BASE_STRING;
    }


    //todo voir prochaine alpha (0.7) pour regarder ce qu'il en devient
    public Optional<JsonObject> getRanks(){
        return Optional.of((JsonObject) getJsonRepresentation().get(RANKS));
    }

    public Optional<Integer> getRelatedFamilyId(){
        if (getJsonRepresentation().has(RELATED_FAMILY)){
            return Optional.of(getJsonRepresentation().get(RELATED_FAMILY).getAsInt());
        }
        return Optional.empty();
    }

    public Optional<Family> getRelatedFamily(){
        return getRelatedFamilyId().map(a -> new Family(a));
    }

    @Override
    public Optional<JsonArray> getArrayChapters() {
        return getArrayChapters(getJsonRepresentation());
    }

    public Optional<Integer> getMinPlayer(){
        if (getJsonRepresentation().has(MIN_PLAYER)){
            return Optional.of(getJsonRepresentation().get(MIN_PLAYER).getAsInt());
        }
        return Optional.empty();
    }

    public Optional<Integer> getMaxPlayer(){
        if (getJsonRepresentation().has(MAX_PLAYER)){
            return Optional.of(getJsonRepresentation().get(MAX_PLAYER).getAsInt());
        }
        return Optional.empty();
    }
}
