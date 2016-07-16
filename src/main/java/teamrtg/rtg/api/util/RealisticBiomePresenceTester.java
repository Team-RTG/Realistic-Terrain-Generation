package teamrtg.rtg.api.util;

import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.world.biome.RTGBiome;

import static net.minecraft.world.biome.Biome.getIdForBiome;

/**
 * @author WhichOnesPink
 */
public class RealisticBiomePresenceTester {

    public static void doBiomeCheck() {
        Biome[] b = BiomeUtils.getRegisteredBiomes();

        for (Biome aB : b) {
            if (aB != null) {
                int biomeId = getIdForBiome(aB);
                String biomeName = BiomeUtils.getLocForBiome(aB).toString();
                String biomeClass = aB.getBiomeClass().getName();

                RTGBiome rBiome = RTGBiome.forBiome(biomeId);
                if (rBiome == null) {
                    Logger.info("RTG could not find a realistic version of %s (%d). This is expected for non-overworld biomes", biomeName, biomeId, biomeClass);
                }
            }
        }
    }
}
