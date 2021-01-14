package fr.oxal.v2.waven.utils.parser.utils;

import fr.oxal.v2.waven.utils.collections.WavenEntities;

import java.util.Optional;

public interface SecondReferenceParser<T> {
    Optional<T> getSecondObject(String text);
    int getIdSecondObject(String text);
    String getClassNameSecondObject(String text);
    Class<T> getClassSecondObject(String text);
}
