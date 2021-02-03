package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import com.google.gson.JsonObject;
import fr.oxal.v2.waven.utils.element.WavenElement;
import fr.oxal.v2.waven.utils.jsonArgumentEntity.detail.DetailsEntity;

import java.util.Optional;

public interface WithElement extends DetailsEntity {

    String ELEMENT = "element";

    default Optional<Integer> getIdElement() {
        return getDetails().map(a -> a.has(ELEMENT) ? a.get(ELEMENT).getAsInt() : null);
    }

    default Optional<Integer> getIdElement(JsonObject j) {
        if (j.has(ELEMENT)){
            return Optional.of(j.get(ELEMENT).getAsInt());
        }
        return Optional.empty();
    }

    default Optional<WavenElement> getElement(){
        return getIdElement().map(a -> new WavenElement(a));
    }

    default Optional<String> getNameElement(){
        return getElement().map(a -> a.getName());
    }

    default boolean hasElement(WavenElement e){
        return getElement().map(wavenElement -> wavenElement.equals(e)).orElse(false);
    }
}
