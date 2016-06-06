package teamrtg.rtg.api.world.biome;

import teamrtg.rtg.api.world.biome.deco.DecoBase;

import java.util.ArrayList;

/**
 * @author topisani
 */
public interface IHasDecos {

    void initDecos();

    ArrayList<DecoBase> getDecos();

    /**
     * Adds a deco object to the list of biome decos.
     * @param deco
     */
    default void addDeco(DecoBase deco) {
        this.getDecos().add(deco);
    }

}
