package fr.oxal.v2.waven.utils.jsonArgumentEntity.detail;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.utils.dictionary.DictionaryFabric;

import java.util.Optional;

import static fr.oxal.v2.waven.utils.dictionary.HaveDictionary.UI;

public interface WithElement extends DetailsEntity {

    String ELEMENT = "element";
    String REF_NAME_DICO = "UI";

    default Optional<Integer> getElement() {
        return getDetails().map(a -> a.has(ELEMENT) ? a.get(ELEMENT).getAsInt() : null);
    }

    default Optional<String> getNameElement(){
        return getElement().map(a -> getElementNameById(a));
    }

    default String getElementNameById(int id) {
        switch (id) {
            case 1:
                return DictionaryFabric.getDictionary(REF_NAME_DICO, Wavenpedia.dictionaryPath + UI).get("94269").getAsString();
            case 2:
                return DictionaryFabric.getDictionary(REF_NAME_DICO, Wavenpedia.dictionaryPath + UI).get("4241").getAsString();
            case 3:
                return DictionaryFabric.getDictionary(REF_NAME_DICO, Wavenpedia.dictionaryPath + UI).get("71144").getAsString();
            case 4:
                return DictionaryFabric.getDictionary(REF_NAME_DICO, Wavenpedia.dictionaryPath + UI).get("15128").getAsString();
            case 6:
                return DictionaryFabric.getDictionary(REF_NAME_DICO, Wavenpedia.dictionaryPath + UI).get("43151").getAsString();
            default:
                System.err.println("erreur id element : " + id);
        }
        return null;
    }
}
