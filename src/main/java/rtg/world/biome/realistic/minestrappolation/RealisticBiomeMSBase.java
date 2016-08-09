package rtg.world.biome.realistic.minestrappolation;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.minestrappolation.config.BiomeConfigMS;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMSBase extends RealisticBiomeBase {

    public static RealisticBiomeBase msRedwoodForest;
    public static RealisticBiomeBase msTheFrost;

    public RealisticBiomeMSBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("ministrapp")) {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();

            for (int i = 0; i < 256; i++) {
                if (b[i] != null) {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }

                    BiomeGenBase msBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();

                    if (biomeName == "Redwood Forest" && biomeClass == "minestrapteam.mods.minestrappolation.world.biomes.BiomeRedwood") {
                        msRedwoodForest = new RealisticBiomeMSRedwoodForest(msBiome, BiomeConfigMS.biomeConfigMSRedwoodForest);
                    }
                    else if (biomeName == "The Frost" && biomeClass == "minestrapteam.mods.minestrappolation.world.biomes.BiomeFrost") {
                        msTheFrost = new RealisticBiomeMSTheFrost(msBiome, BiomeConfigMS.biomeConfigMSTheFrost);
                    }
                }
            }
        }
    }
}
