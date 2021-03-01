package fr.oxal.v2.waven.entity.pvm.resource.astre;

import fr.oxal.v2.Wavenpedia;
import fr.oxal.v2.waven.entity.pvm.resource.Resource;

public class RingResource extends Resource {

    public static final String RING_RESOURCE_PATH = Wavenpedia.jsonPath + "RingResourceDefinition/";

    public RingResource(int id) {
        super(id);
    }

    @Override
    public String getPathFolder() {
        return RING_RESOURCE_PATH;
    }
}
