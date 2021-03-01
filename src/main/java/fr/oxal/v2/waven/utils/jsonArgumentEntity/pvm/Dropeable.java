package fr.oxal.v2.waven.utils.jsonArgumentEntity.pvm;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.pvm.drop.SingleObtainableItemListDefinition;
import fr.oxal.v2.waven.entity.pvm.fight.chapter.Chapter;
import fr.oxal.v2.waven.entity.pvm.fight.dungeon.DungeonZone;
import fr.oxal.v2.waven.entity.pvm.fight.quest.Quest;
import fr.oxal.v2.waven.utils.collections.WavenEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface Dropeable extends Rewardeable {

    List<SingleObtainableItemListDefinition> getDropZone();

    default List<SingleObtainableItemListDefinition> getDropZone(WavenEntity wavenEntity) {
        ArrayList<SingleObtainableItemListDefinition> list = new ArrayList<>();
        for (SingleObtainableItemListDefinition s : WavenEntities.getAll(SingleObtainableItemListDefinition.class, WavenEntity::isAvailable)) {
            if (s.contain(wavenEntity)) {
                list.add(s);
            }
        }
        return list;
    }

    default boolean canDrop() {
        return !getDropZone().isEmpty() || !getDungeonZones().isEmpty() || !getQuestZones().isEmpty();
    }

    default String getDropText() {
        List<SingleObtainableItemListDefinition> listItem = getDropZone();
        Map<DungeonZone, List<Chapter>> listDungeon = getDungeonMap();
        Map<Quest, List<Chapter>> listQuest = getQuestMap();


        StringBuilder builder = new StringBuilder();
        if (isWavenEntity()) {
            builder.append(asWavenEntity().getDisplayName() + "\n");
        } else {
            builder.append(getGlobalParsedName(0) + "\n");
        }

        builder.append("\tMOB & MERCHANT\n");
        builder.append("\t\t" + listItem.stream().map(a -> a.getDisplayName()).collect(Collectors.joining("\n\t\t")));
        builder.append("\n\tQUEST\n");
        builder.append("\t\t" + listQuest.entrySet().stream().map(entry -> entry.getKey().getDisplayName() + "chapitre : " + entry.getValue().stream().filter(a -> a.getChapterIndex().isPresent()).map(a -> a.getChapterIndex().get() + "").collect(Collectors.joining(", "))).collect(Collectors.joining("\n\t\t")));
        builder.append("\n\tDUNGEON\n");
        builder.append("\t\t" + listDungeon.entrySet().stream().map(entry -> entry.getKey().getDisplayName() + " chapitre : " + entry.getValue().stream().filter(a -> a.getChapterIndex().isPresent()).map(a -> a.getChapterIndex().get() + "").collect(Collectors.joining(", "))).collect(Collectors.joining("\n\t\t")));

        return builder.toString();
    }
}
