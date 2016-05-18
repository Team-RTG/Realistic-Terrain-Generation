package teamrtg.rtg.api.util.genlayers;

import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.BiomeProviderBOP;
import net.minecraft.world.gen.layer.GenLayer;

/**
 * All of this is BOP code, i just had to remove the need for a BOPWorldType and such
 * @author topisani
 */
public class BOPGenLayers {

    public static GenLayer[] setupBOPGenLayers(long worldSeed) {
        BOPWorldSettings settings = new BOPWorldSettings();
        return BiomeProviderBOP.setupBOPGenLayers(worldSeed, settings);
    }
}
