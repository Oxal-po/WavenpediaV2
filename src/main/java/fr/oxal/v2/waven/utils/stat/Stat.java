package fr.oxal.v2.waven.utils.stat;

import com.google.gson.JsonObject;
import fr.oxal.v2.utils.math.WavenMath;

import java.util.List;

public class Stat {

    private String name;
    private List<Number> values;

    public static final String LIFE = "life";
    public static final String ATTACK = "actionValue";
    public static final String PM = "movementPoints";
    public static final String SHIELD = "baseMecaLife";
    public static final String CRIT_DMG = "physicalCriticalDamage";
    public static final String CRIT_CHANCE = "physicalCriticalChance";

    public Stat(String name, List<Number> values) {
        this.name = name;
        this.values = values;
    }
}
