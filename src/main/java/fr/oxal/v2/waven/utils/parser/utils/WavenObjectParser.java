package fr.oxal.v2.waven.utils.parser.utils;

import java.util.Optional;

public interface WavenObjectParser<T> {
    Optional<T> getObject(String text);
}
