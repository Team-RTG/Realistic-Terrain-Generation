package rtg.util.genlayers;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;
import rtg.config.rtg.ConfigRTG;
import rtg.util.ModPresenceTester;

public class GenLayerUtils {

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, String worldConfig) {
        if (new ModPresenceTester("BiomesOPlenty").present() && ConfigRTG.useBOPLayouts) return BOPGenLayers.setupBOPGenLayers(seed);
        else return GenLayer.initializeAllBiomeGenerators(seed, worldType, worldConfig);
    }


}
