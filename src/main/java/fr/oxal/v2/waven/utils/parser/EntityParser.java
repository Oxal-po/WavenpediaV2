package fr.oxal.v2.waven.utils.parser;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static fr.oxal.v2.waven.utils.parser.CommonParser.getTextByRegex;

public class EntityParser extends AbstractParser{

    public final static String REGEX_GLOBAL_ENTITY = "\\{[aA-zZ]+:([0-9]+)(\\[.*\\])?\\}";
    public final static String REGEX_NOT_OVERRIDE_ENTITY = "\\{[aA-zZ]+:([0-9]+)\\}";
    public final static String REGEX_OVERRIDE_ENTITY = "\\{[aA-zZ]+:[0-9]+\\[(.*)\\]\\}";
    public final static String REGEX_SECOND_ENTITY = "[aA-zZ]+";

    public String getEntityId(String text){
        Optional<String> s = getTextByRegex(text,getNotOverrideRegex(), 1);
        if (s.isPresent()){
            return s.get();
        }
        return WavenEntity.NOT_ENTITY + "";
    }

    public Optional<NamedEntity> getEntityByText(String text){
        List<NamedEntity> list = Wavenpedia.classedMappedEntity
                .entrySet()
                .stream()
                .filter(entry -> text.contains(entry.getKey().getSimpleName()))
                .map(entry -> WavenEntities.construct(entry.getKey(), Integer.parseInt(getEntityId(text))))
                .filter(Optional::isPresent)
                .map(o -> (NamedEntity) o.get())
                .collect(Collectors.toList());
        if (list.size() == 1){
            return Optional.of(list.get(0));
        }else if (list.size() > 1){
            System.err.println("erreur WavenParser : parseEntity -> taille list > 1");
        }
        return Optional.empty();
    }

    @Override
    public String getGlobalRegex() {
        return REGEX_GLOBAL_ENTITY;
    }

    @Override
    public String getNotOverrideRegex() {
        return REGEX_NOT_OVERRIDE_ENTITY;
    }

    @Override
    public String getOverrideRegex() {
        return REGEX_OVERRIDE_ENTITY;
    }

    @Override
    public String getText(String text) {
        Optional<NamedEntity> o = getEntityByText(text);
        if (o.isPresent()){
            return o.get().getName();
        }
        return NamedEntity.BASE_STRING;
    }
}
