package teamrtg.rtg.api.biome;

import teamrtg.rtg.world.gen.deco.DecoBase;

/**
 * @author topisani
 */
public interface IDecoratable {

    void initDecos();

    /**
     * Adds a deco object to the list of biome decos.
     * @param deco
     */
    void addDeco(DecoBase deco);

}
