package rtg.world.biome.realistic.afraidofthedark;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.afraidofthedark.config.BiomeConfigAOTD;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeAOTDBase extends RealisticBiomeBase {

    public static RealisticBiomeBase aotdErieForest;

    public RealisticBiomeAOTDBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("AfraidOfTheDark")) {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();

            for (int i = 0; i < 256; i++) {
                if (b[i] != null) {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }

                    BiomeGenBase aotdBiome = b[i];
                    String biomeName = aotdBiome.biomeName;
                    String biomeClass = aotdBiome.getBiomeClass().getName();

                    if (biomeName == "Erie Forest" && biomeClass == "com.DavidM1A2.AfraidOfTheDark.common.biomes.BiomeErieForest") {
                        aotdErieForest = new RealisticBiomeAOTDErieForest(aotdBiome, BiomeConfigAOTD.biomeConfigAOTDErieForest);
                    }
                }
            }
        }
    }
}