package fr.oxal.v2.waven.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static fr.oxal.v2.waven.element.WavenElement.*;

public class WavenElements {

    public final static int[] ALL_ELEMENT = new int[]{FIRE, WATER, EARTH, WIND, NEUTRAL, PA};
    public final static int[] ALL_GAUGE = new int[]{ADD_RESERVE, ADD_FIRE, ADD_WATER, ADD_EARTH, ADD_WIND, ADD_NEUTRAL};

    public static Optional<WavenElement> getElementByName(String name){
        for (int i : ALL_ELEMENT){
            WavenElement e = new WavenElement(i);
            if (name.toLowerCase().equals(e.getParsedName().toLowerCase())){
                return Optional.of(e);
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
}
