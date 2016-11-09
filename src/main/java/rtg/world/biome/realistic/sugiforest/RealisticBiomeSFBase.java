package rtg.world.biome.realistic.sugiforest;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.sugiforest.config.BiomeConfigSF;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeSFBase extends RealisticBiomeBase {

    public static RealisticBiomeBase sfSugiForest;

    public RealisticBiomeSFBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("sugiforest")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Sugi Forest") && biomeClass.equals("sugiforest.world.BiomeSugiForest")) {
                    sfSugiForest = new RealisticBiomeSFSugiForest(biome, BiomeConfigSF.biomeConfigSFSugiForest);
                }
            }
        }
    }
}
