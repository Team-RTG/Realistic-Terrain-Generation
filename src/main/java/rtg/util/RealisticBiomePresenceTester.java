package rtg.util;

import net.minecraft.world.biome.Biome;

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

            Logger.debug("Biome (" + biomeId + ") " + biomeName + " from " + biomeClass);

            switch (biomeId) {

                case 8:     // The Nether
                case 9:     // The End
                case 127:   // The Void

                    // Do nothing.
                    break;

                default:

                    try {
                        RealisticBiomeBase rBiome = RealisticBiomeBase.getBiome(biomeId);
                        String rBiomeName = rBiome.config.biomeSlug;
                    }
                    catch (Exception e) {
                        Logger.warn("WARNING! RTG could not find a realistic version of %s (%d) from %s", biomeName, biomeId, biomeClass);
                    }

                    break;
            }
        }
    }
}
