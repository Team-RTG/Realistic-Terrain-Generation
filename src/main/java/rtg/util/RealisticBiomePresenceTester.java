package rtg.util;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * @author WhichOnesPink
 */
public class RealisticBiomePresenceTester {

    public static void doBiomeCheck() {

        BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();

        for (int i = 0; i < 256; i++) {
            if (b[i] != null) {
                BiomeGenBase biome = b[i];
                int biomeId = b[i].biomeID;
                String biomeName = b[i].biomeName;
                String biomeClass = b[i].getBiomeClass().getName();

                //Logger.info("Biome (" + biomeId + ") " + biomeName + " from " + biomeClass);

                switch (biomeId) {

                    case 8:
                    case 9:
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
}
