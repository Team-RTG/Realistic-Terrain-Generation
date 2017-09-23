package rtg.util;

import net.minecraft.world.biome.Biome;

import rtg.api.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 *
 * @author WhichOnesPink
 * @author srs_bsns
 */
public class RealisticBiomePresenceTester {

    public static void doBiomeCheck()
    {
        for (Biome biome : Biome.REGISTRY)
        {
            int biomeId = Biome.getIdForBiome(biome);
            String biomeName = biome.getBiomeName();
            String biomeClass = biome.getBiomeClass().getName();
            boolean isRealistic = true;

            switch (biomeId) {

                case 8:     // The Nether
                case 9:     // The End
                case 127:   // The Void

                    // Do nothing.
                    break;

                default:

                    try {
                        isRealistic = RealisticBiomeBase.isRealisticBiome(biomeId);
                    }
                    catch (Exception e) {
                        isRealistic = false;
                    }

                    if (isRealistic) {
                        RealisticBiomeBase rbb = RealisticBiomeBase.getBiome(biomeId);
                        Logger.info("Found biome (%d) %s from %s with a %s beach.", biomeId, biomeName, biomeClass, rbb.beachBiome().getBiomeName());
                    }
                    else {
                        Logger.warn("WARNING! RTG could not find a realistic version of %s (%d) from %s. (If %s is a non-Overworld biome, then this is not an error.)", biomeName, biomeId, biomeClass, biomeName);
                    }

                    break;
            }
        }
    }
}
