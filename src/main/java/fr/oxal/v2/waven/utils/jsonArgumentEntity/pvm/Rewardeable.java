package fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.pvm.fight.chapter.Chapter;
import fr.oxal.v2.waven.entity.pvm.fight.dungeon.DungeonZone;
import fr.oxal.v2.waven.entity.pvm.fight.quest.Quest;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Rewardeable extends NamedEntity {

    default List<DungeonZone> getDungeonZones() {
        ArrayList<DungeonZone> list = new ArrayList<>();

        for (DungeonZone d : WavenEntities.getAll(DungeonZone.class, WavenEntity::isAvailable)) {
            if (this.isWavenEntity() && d.getChapters(asWavenEntity()).size() > 0) {
                list.add(d);
            }
        }

        return list;
    }

    default List<Chapter> getDungeonChapters() {
        ArrayList<Chapter> list = new ArrayList<>();

        for (DungeonZone d : WavenEntities.getAll(DungeonZone.class, WavenEntity::isAvailable)) {
            list.addAll(d.getChapters(asWavenEntity()));
        }

        return list;
    }

    default Map<DungeonZone, List<Chapter>> getDungeonMap() {
        HashMap<DungeonZone, List<Chapter>> map = new HashMap<>();

        for (DungeonZone d : WavenEntities.getAll(DungeonZone.class, WavenEntity::isAvailable)) {
            if (this.isWavenEntity() && d.getChapters(asWavenEntity()).size() > 0) {
                map.put(d, d.getChapters(asWavenEntity()));
            }
        }

        return map;
    }

    default List<Quest> getQuestZones() {
        ArrayList<Quest> list = new ArrayList<>();

        for (Quest d : WavenEntities.getAll(Quest.class, WavenEntity::isAvailable)) {
            if (this.isWavenEntity() && d.getChapters(asWavenEntity()).size() > 0) {
                list.add(d);
            }
        }

        return list;
    }

    default List<Chapter> getQuestChapters() {
        ArrayList<Chapter> list = new ArrayList<>();

        for (Quest d : WavenEntities.getAll(Quest.class, WavenEntity::isAvailable)) {
            list.addAll(d.getChapters(asWavenEntity()));
        }

        return list;
    }

    default Map<Quest, List<Chapter>> getQuestMap() {
        HashMap<Quest, List<Chapter>> map = new HashMap<>();

        for (Quest d : WavenEntities.getAll(Quest.class, WavenEntity::isAvailable)) {
            if (this.isWavenEntity() && d.getChapters(asWavenEntity()).size() > 0) {
                map.put(d, d.getChapters(asWavenEntity()));
            }
        }

        return map;
    }
}
