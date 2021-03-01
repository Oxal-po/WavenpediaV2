package fr.oxal.v2;

import fr.oxal.v2.waven.WavenEntity;
import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.entity.WavenInterface;
import fr.oxal.v2.waven.utils.collections.WavenEntities;
import fr.oxal.v2.waven.utils.dictionary.NamedEntity;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest{

    private final static String FORMAT_ERROR = "error %s : \n\tclass : %s\n\tid : %d\n\tnameID : %d\n\tdescriID : %d";
    private final static String FORMAT_ERROR_2 = "\t\tname : %s\n\t\tdescri : %s";

    public ParserTest() {
        Wavenpedia.setConstPath("../data7/json/ConstantsDefinition/25.json");
        Wavenpedia.setDictionaryPath("../data7/json/dico/");
        Wavenpedia.setJsonPath("../data7/json/");
        Wavenpedia.setKeyWordPath("../data7/json/KeyWord/");
        Wavenpedia.setImagePath("../data/imagesV4/");
        Wavenpedia.start();
    }

    public void baseTest(Class<? extends NamedEntity>[] classes, Predicate<NamedWavenEntity> predicate, Function<NamedWavenEntity, String> notParse, Function<NamedWavenEntity, String> parse) {
        Throwable t = new Throwable();
        t.fillInStackTrace();
        StackTraceElement elem = t.getStackTrace()[1];
        String functionName = elem.getMethodName();

        StringBuilder builder = new StringBuilder();
        for (Class<? extends NamedEntity> c : classes) {
            for (NamedWavenEntity n : WavenEntities.getAll((Class<NamedWavenEntity>) c, predicate)) {
                try {
                    notParse.apply(n);
                    parse.apply(n);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    builder.append(String.format(FORMAT_ERROR, functionName, n.getClass().getSimpleName(),
                            n.getId(), n.getNameId(), n.getDescriptionId()) + "\n");
                    try {
                        builder.append(String.format(FORMAT_ERROR_2, n.getName(), n.getDescription()) + "\n");
                    } catch (NullPointerException ex) {
                    }
                }
            }
        }
        assertEquals("", builder.toString());
    }

    @Test
    public void testAllNameParser() {
        baseTest(Wavenpedia.ALL_NAMED_CLASS, a -> true, NamedWavenEntity::getName, NamedEntity::getParsedName);
    }


    @Test
    public void testAllDescriptionParser() {
        baseTest(Wavenpedia.ALL_NAMED_CLASS, a -> true, NamedWavenEntity::getDescription, NamedEntity::getParsedDescription);
    }

    @Test
    public void testAvailableNameParser() {
        baseTest(Wavenpedia.ALL_NAMED_CLASS, WavenEntity::isAvailable, NamedWavenEntity::getName, NamedEntity::getParsedName);
    }


    @Test
    public void testAvailableDescriptionParser() {
        baseTest(Wavenpedia.ALL_NAMED_CLASS, WavenEntity::isAvailable, NamedWavenEntity::getDescription, NamedEntity::getParsedDescription);
    }


    @Test
    public void testAvailableMakeJson() {
        for (Class<? extends NamedWavenEntity> c : Wavenpedia.ALL_NAMED_CLASS) {
            WavenEntities.getAll(c, WavenEntity::isAvailable).forEach(WavenInterface::transformToJson);
        }
    }


    @Test
    public void testAllMakeJson() {
        for (Class<? extends NamedWavenEntity> c : Wavenpedia.ALL_NAMED_CLASS) {
            WavenEntities.getAll(c).forEach(WavenInterface::transformToJson);
        }
    }


}
