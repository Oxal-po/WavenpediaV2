package fr.oxal.v2.waven.utils.dictionary;

import com.google.gson.JsonObject;

public interface HaveDictionary {

    String EFFECTS = "Effects.json";
    String ADVENTURE = "Adventures.json";
    String APPLICATION = "Application.json";
    String CHARACTER_SKIN = "CharacterSkins.json";
    String COMPANION = "Companions.json";
    String EQUIPMENT = "Equipments.json";
    String FIGHT = "Fight.json";
    String GOD = "Gods.json";
    String MECHANISM = "Mechanisms.json";
    String SPELL = "Spells.json";
    String SUMMONING = "Summonings.json";
    String UI = "UI.json";
    String WEAPON = "Weapons.json";
    String WORLD = "Worlds.json";
    String RESOURCES = "Resources.json";
    String SKILLS = "Skills.json";

    String getNameDictionnaire();

    JsonObject getDictionary();

    String getText(long id);
}
