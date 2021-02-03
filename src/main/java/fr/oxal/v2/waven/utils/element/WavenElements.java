package fr.oxal.v2.waven.utils.element;

import java.util.Optional;

import static fr.oxal.v2.waven.utils.element.WavenElement.ALL_ELEMENT;

public class WavenElements {

    public static Optional<WavenElement> getElementByName(String name){
        for (int i : ALL_ELEMENT){
            WavenElement e = new WavenElement(i);
            if (name.toLowerCase().equals(e.getParsedName().toLowerCase())){
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }
}
