package teamrtg.rtg.api.util;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.util.debug.Logger;

import static net.minecraft.world.biome.BiomeGenBase.getIdForBiome;

/**
 * @author WhichOnesPink
 */
public class RealisticBiomePresenceTester {

    public static void doBiomeCheck() {
        BiomeGenBase[] b = BiomeUtils.getRegisteredBiomes();

        for (int i = 0; i < b.length; i++) {
            if (b[i] != null) {
                BiomeGenBase biome = b[i];
                int biomeId = getIdForBiome(b[i]);
                String biomeName = BiomeUtils.getLocForBiome(b[i]).toString();
                String biomeClass = b[i].getBiomeClass().getName();

                switch (biomeId) {

                    case 8:
                    case 9:
                        // Do nothing.
                        break;

                    default:

                        try {
                            RealisticBiomeBase rBiome = RealisticBiomeBase.forBiome(biomeId);
                        } catch (Exception e) {
                            Logger.info("RTG could not find a realistic version of %s (%d) from %s", biomeName, biomeId, biomeClass);
                        }

                        break;
                }
            }
        }
    }
}
