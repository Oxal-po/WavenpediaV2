package fr.oxal.v2.waven.utils.element;

import com.google.gson.JsonObject;
import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.effect.WavenEffect;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.dictionary.DictionaryFabric;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static fr.oxal.v2.waven.utils.dictionary.HaveDictionary.UI;

public class WavenElement implements WavenInterface , NamedEntity {

    private int id, value;
    public final static String REF_NAME_DICO = "UI";
    public final static int PA = -1;
    public final static int FIRE = 1;
    public final static int ADD_FIRE = 11;
    public final static int WATER = 2;
    public final static int ADD_WATER = 12;
    public final static int EARTH = 3;
    public final static int ADD_EARTH = 13;
    public final static int WIND = 4;
    public final static int ADD_WIND = 14;
    public final static int NEUTRAL = 6;
    public final static int ADD_NEUTRAL = 57;
    public final static int ADD_RESERVE = 19;
    public final static int[] ALL_ELEMENT = new int[]{FIRE, WATER, EARTH, WIND, NEUTRAL, PA};
    public final static int[] ALL_GAUGE = new int[]{ADD_RESERVE, ADD_FIRE, ADD_WATER, ADD_EARTH, ADD_WIND, ADD_NEUTRAL};


    public WavenElement(int id) {
        this.id = id;
    }

    @Override
    public long getNameId() {
        switch (id) {
            case FIRE:
            case ADD_FIRE:
                return 94269;
            case WATER:
            case ADD_WATER:
                return 4241;
            case EARTH:
            case ADD_EARTH:
                return 71144;
            case WIND:
            case ADD_WIND:
                return 15128;
            case NEUTRAL:
            case ADD_NEUTRAL:
                return 43151;
            case ADD_RESERVE:
                return 15321;
            case PA:
                return 96095;
            default:
                System.err.println("erreur id element : " + id);
        }
        return 0;
    }

    @Override
    public long getDescriptionId() {
        return 0;
    }

    @Override
    public String getName() {
        if (isPa()){
            return DictionaryFabric.getDictionary(WavenEffect.class, Wavenpedia.dictionaryPath + EFFECTS).get(getNameId() + "").getAsString();
        }
        return getText(getNameId());
    }

    @Override
    public String getDescription() {
        return NamedEntity.BASE_STRING;
    }

    @Override
    public String getNameDictionnaire() {
        return REF_NAME_DICO;
    }

    @Override
    public JsonObject getDictionary() {
        return DictionaryFabric.getDictionary(getNameDictionnaire(), Wavenpedia.dictionaryPath + UI);
    }

    @Override
    public String getText(long id) {
        return getDictionary().get(id + "").getAsString();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPa(){
        return id == PA;
    }

    @Override
    public String toString() {
        return value + " : " + getName();
    }

    public static boolean haveElement(String text){
        for (int i : ALL_ELEMENT){
            WavenElement e = new WavenElement(i);
            if (text.toLowerCase().contains(e.getParsedName().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Optional<WavenElement> getElementByText(String text){
        Pattern p;
        Matcher m;
        for (int i : ALL_ELEMENT){
            WavenElement e = new WavenElement(i);
            p = Pattern.compile(".*([0-9])\\\\\\_" + e.getParsedName().toLowerCase() + "<.*");
            m = p.matcher(text.toLowerCase());
            if (m.find()){
                e.setValue(Integer.parseInt(m.group(1)));
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    public static Optional<WavenElement> getModifierElement(String text, WavenElement e){
        Optional<WavenElement> o = getElementByText(text);
        if (o.isPresent()){
            if (o.get().equals(e)){
                WavenElement el = new WavenElement(e.getId());
                el.setValue(e.getValue() - o.get().getValue());
                return Optional.of(el);
            }
        }

        return Optional.empty();
    }

    public static List<WavenElement> getAllElement(){
        ArrayList<WavenElement> elem = new ArrayList<>();
        for (int i : ALL_ELEMENT){
            elem.add(new WavenElement(i));
        }
        return elem;
    }

    public static List<String> getAllElementName(){
        return getAllElement().stream().map(a -> a.getName()).collect(Collectors.toList());
    }

    public static List<WavenElement> getAllGauge(){
        ArrayList<WavenElement> elem = new ArrayList<>();
        for (int i : ALL_GAUGE){
            elem.add(new WavenElement(i));
        }
        return elem;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WavenElement)) return false;
        WavenElement e = (WavenElement) o;
        if (id == e.getId() || (id - 10) == e.getId() || (id + 10) == e.getId()){
            return true;
        }

        return super.equals(o);
    }

    public boolean isFire(){
        return getId() == FIRE || getId() == ADD_FIRE;
    }

    public boolean isWater(){
        return getId() == WATER || getId() == ADD_WATER;
    }

    public boolean isEarth(){
        return getId() == EARTH || getId() == ADD_EARTH;
    }

    public boolean isWind(){
        return getId() == WIND || getId() == ADD_WIND;
    }

    public boolean isNeutral(){
        return getId() == NEUTRAL || getId() == ADD_NEUTRAL;
    }
}
