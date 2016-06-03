package teamrtg.rtg.api.util;

import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.world.biome.RTGBiomeBase;

import static net.minecraft.world.biome.Biome.getIdForBiome;

/**
 * @author WhichOnesPink
 */
public class RealisticBiomePresenceTester {

    public static void doBiomeCheck() {
        Biome[] b = BiomeUtils.getRegisteredBiomes();

        for (int i = 0; i < b.length; i++) {
            if (b[i] != null) {
                Biome biome = b[i];
                int biomeId = getIdForBiome(biome);
                String biomeName = BiomeUtils.getLocForBiome(biome).toString();
                String biomeClass = biome.getBiomeClass().getName();

                RTGBiomeBase rBiome = RTGBiomeBase.forBiome(biomeId);
                if (rBiome == null) {
                    Logger.info("RTG could not find a realistic version of %s (%d). This is expected for non-overworld biomes", biomeName, biomeId, biomeClass);
                }
            }
        }
    }
}
