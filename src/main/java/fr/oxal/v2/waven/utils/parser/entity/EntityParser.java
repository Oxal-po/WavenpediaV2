package fr.oxal.v2.waven.utils.parser.entity;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import fr.oxal.v2.waven.utils.parser.ParserUtils;
import fr.oxal.v2.waven.utils.parser.WavenEntityParser;
import fr.oxal.v2.waven.utils.parser.utils.OverrideableParser;
import fr.oxal.v2.waven.utils.parser.utils.WavenObjectParser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static fr.oxal.v2.waven.utils.parser.ParserUtils.getTextByRegex;

public class EntityParser extends WavenEntityParser implements OverrideableParser, WavenObjectParser<NamedWavenEntity> {

    public final static String REGEX_GLOBAL_ENTITY = "\\{[aA-zZ]+:([0-9]+)(\\[.*\\])?!?\\}";
    public final static String REGEX_NOT_OVERRIDE_ENTITY = "\\{[aA-zZ]+:([0-9]+)!?\\}";
    public final static String REGEX_OVERRIDE_ENTITY = "\\{[aA-zZ]+:[0-9]+\\[(.*)\\]!?\\}";

    @Override
    public String parse(String text) {
        if (isOverride(text)){
            return getOverride(text);
        }else if (getObject(text).isPresent()){
            return getObject(text).get().getName();
        }else{
            System.err.println("erreur EntityParser : l'objet n'a pas été reconnu : " + text);
        }
        return null;
    }

    @Override
    public boolean canParse(String text) {
        return text.matches(REGEX_GLOBAL_ENTITY);
    }

    @Override
    public boolean isOverride(String text) {
        return text.matches(REGEX_OVERRIDE_ENTITY);
    }

    @Override
    public String getOverride(String text) {
        return ParserUtils.getText(text, REGEX_OVERRIDE_ENTITY, 1);
    }

    @Override
    public Optional<NamedWavenEntity> getObject(String text) {
        List<NamedEntity> list = Wavenpedia.classedMappedEntity
                .entrySet()
                .stream()
                .filter(entry -> text.contains(entry.getKey().getSimpleName()) || text.toLowerCase().contains(entry.getKey().getSimpleName().toLowerCase()))
                .map(entry -> WavenEntities.construct(entry.getKey(), Integer.parseInt(getEntityId(text))))
                .filter(Optional::isPresent)
                .map(o -> (NamedEntity) o.get())
                .collect(Collectors.toList());
        if (list.size() == 1){
            return Optional.of(list.get(0).asNamedWavenEntity());
        }else {
            System.err.println("erreur EntityParser : Object introuvable");
        }

        return Optional.empty();
    }

    private String getEntityId(String text){
        Optional<String> s = getTextByRegex(text,REGEX_NOT_OVERRIDE_ENTITY, 1);
        return s.orElse(WavenEntity.NOT_ENTITY + "");
    }

}
