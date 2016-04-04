package rtg.util.genlayers;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import rtg.api.util.ModPresenceTester;
import rtg.api.mods.Mods;

public class GenLayerUtils {

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, String worldConfig) {
        if (new ModPresenceTester("BiomesOPlenty").present() && Mods.RTG.config.USE_BOP_LAYOUT.get())
            return BOPGenLayers.setupBOPGenLayers(seed);
        else return GenLayer.initializeAllBiomeGenerators(seed, worldType, worldConfig);
    }


}
