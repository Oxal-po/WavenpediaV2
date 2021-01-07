package fr.oxal.v2.waven.entity.base.StatEntity;

import fr.oxal.v2.waven.entity.NamedWavenEntity;
import fr.oxal.v2.waven.utils.stat.Stat;
import fr.oxal.v2.waven.utils.stat.WithStat;

import java.util.ArrayList;
import java.util.List;

public abstract class StatEntity extends NamedWavenEntity implements WithStat {

    private List<Stat> stats;

    public StatEntity(int id) {
        super(id);
        stats = new ArrayList<>();
    }
}
