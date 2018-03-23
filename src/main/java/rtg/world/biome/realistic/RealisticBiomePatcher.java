package rtg.world.biome.realistic;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.Logger;
import rtg.api.util.WorldUtil;


public class RealisticBiomePatcher {

    private RealisticBiomeBase realisticBiome;
    private RTGConfig rtgConfig = RTGAPI.config();

    public RealisticBiomePatcher() {

        Biome biome = WorldUtil.Biomes.getBiomeFromCfgString(rtgConfig.PATCH_BIOME.get().trim(), Biomes.PLAINS);
        this.realisticBiome = RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome));
        if (this.realisticBiome == null) {
            Logger.error("Erroneous patch biome set: [{}], This biome can't not be used as a patch biome as it is unsupported.", biome.getRegistryName());
        }
    }

    public RealisticBiomeBase getPatchedRealisticBiome(String exceptionMessage) {

        if (rtgConfig.USE_PATCH_BIOME.get() && this.realisticBiome != null) { return this.realisticBiome; }
        throw new RuntimeException(exceptionMessage);
    }

    public Biome getPatchedBaseBiome(String exceptionMessage) {

        if (rtgConfig.USE_PATCH_BIOME.get() && this.realisticBiome != null) { return this.realisticBiome.baseBiome; }
        throw new RuntimeException(exceptionMessage);
    }
}
