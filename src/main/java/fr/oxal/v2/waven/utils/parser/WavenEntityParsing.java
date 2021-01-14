package fr.oxal.v2.waven.utils.parser;

import fr.oxal.v2.waven.utils.dictionary.NamedEntity;

public abstract class WavenEntityParsing extends Parser{

    private NamedEntity namedEntity;
    private int level;

    @Override
    public void setup(Object... objects) {
        if (objects.length == 2){
            if (objects[0] instanceof NamedEntity && objects[1] instanceof Integer){
                namedEntity = (NamedEntity) objects[0];
                level = (int) objects[1];
            }else{
                System.err.println("erreur EntityParser : mauvais typage");
            }
        }else{
            System.err.println("erreur EntityParser : mauvais argument");
        }
    }

    public void setNamedEntity(NamedEntity namedEntity) {
        this.namedEntity = namedEntity;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public NamedEntity getNamedEntity() {
        return namedEntity;
    }

    public int getLevel() {
        return level;
    }
}
