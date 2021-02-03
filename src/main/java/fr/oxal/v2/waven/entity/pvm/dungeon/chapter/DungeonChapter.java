package fr.oxal.v2.waven.entity.pvm.dungeon.chapter;

import java.util.ArrayList;

//todo rajouter NamedEntity plus tard quand j'aurais le dico pour cette classe
public class DungeonChapter {

    private int chapterId;
    private int i18nChapterDescriptionId;
    private int duration;
    private int difficulty;
    private ArrayList<Integer> rewards;

    public DungeonChapter() {
        rewards = new ArrayList<>();
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getI18nChapterDescriptionId() {
        return i18nChapterDescriptionId;
    }

    public void setI18nChapterDescriptionId(int i18nChapterDescriptionId) {
        this.i18nChapterDescriptionId = i18nChapterDescriptionId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<Integer> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Integer> rewards) {
        this.rewards = rewards;
    }
}
