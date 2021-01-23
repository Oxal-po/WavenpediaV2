package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import fr.oxal.v2.waven.utils.element.WavenElement;

import java.util.Optional;

public interface WithElement extends DetailsEntity {

    String ELEMENT = "element";

    default Optional<Integer> getIdElement() {
        return getDetails().map(a -> a.has(ELEMENT) ? a.get(ELEMENT).getAsInt() : null);
    }

    default Optional<WavenElement> getElement(){
        return getIdElement().map(a -> new WavenElement(a));
    }

    default Optional<String> getNameElement(){
        return getElement().map(a -> a.getName());
    }


}
