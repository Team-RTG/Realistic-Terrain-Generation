package teamrtg.rtg.api.biome;

import teamrtg.rtg.world.gen.deco.DecoBase;

import java.util.ArrayList;

/**
 * @author topisani
 */
public interface IDecoratable {

    ArrayList<DecoBase> initDecos();

    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     * @param deco
     */
    void addDeco(DecoBase deco);

    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome forgeConfig booleans dynamically when configuring the decos in the biome.
     * @param deco
     * @param allowed
     */
    void addDeco(DecoBase deco, boolean allowed);

}
