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

            switch (biomeId) {

                case 8:     // The Nether
                case 9:     // The End
                case 127:   // The Void

                    // Do nothing.
                    break;

                default:

                    try {
                        RealisticBiomeBase rBiome = RealisticBiomeBase.getBiome(biomeId);
                        String rBiomeName = rBiome.biomeSlug();

                        Logger.info("Found biome (%d) %s from %s with a %s beach.", biomeId, biomeName, biomeClass, rBiome.beachBiome().getBiomeName());
                    }
                    catch (Exception e) {
                        Logger.warn("WARNING! RTG could not find a realistic version of %s (%d) from %s. (If %s is a non-Overworld biome, then this is not an error.)", biomeName, biomeId, biomeClass, biomeName);
                    }

                    break;
            }
        }
    }
}
