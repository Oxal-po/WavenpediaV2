package fr.oxal.v2.waven.entity.pvm.rule;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.NamedWavenEntity;

public class CustomRule extends NamedWavenEntity {

    public static final String PATH = Wavenpedia.jsonPath + "CustomRuleDefinition/";

    public CustomRule(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return PATH;
    }

    @Override
    public String getNameDictionnaire() {
        return CUSTOM_RULES;
    }
}
