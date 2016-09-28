package rtg.world.biome.realistic.betteragriculture;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.betteragriculture.config.BiomeConfigBA;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

@SuppressWarnings("WeakerAccess")
public class RealisticBiomeBABase extends RealisticBiomeBase {

    public static RealisticBiomeBase baFarmlandBiome;

    public RealisticBiomeBABase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("betteragriculture")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("FarmlandBiome") && biomeClass.equals("betteragriculture.world.biome.FarmlandBiome")) {
                    baFarmlandBiome = new RealisticBiomeBAFarmlandBiome(biome, BiomeConfigBA.biomeConfigBAFarmlandBiome);
                }
            }
        }
    }
}
