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
                int biomeId = getIdForBiome(biome);
                String biomeName = BiomeUtils.getLocForBiome(biome).toString();
                String biomeClass = biome.getBiomeClass().getName();

                RealisticBiomeBase rBiome = RealisticBiomeBase.forBiome(biomeId);
                if (rBiome == null) {
                    Logger.info("RTG could not find a realistic version of %s (%d). This is expected for non-overworld biomes", biomeName, biomeId, biomeClass);
                }
            }
        }
    }
}
