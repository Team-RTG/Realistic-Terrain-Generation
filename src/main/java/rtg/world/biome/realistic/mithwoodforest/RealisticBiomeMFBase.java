package rtg.world.biome.realistic.mithwoodforest;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.mithwoodforest.config.BiomeConfigMF;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

@SuppressWarnings("WeakerAccess")
public abstract class RealisticBiomeMFBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mfMithwoodForest;

    public RealisticBiomeMFBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("mithwoodforest")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Mithwood Forest") && biomeClass.equals("rainbeau.mithwoodforest.RMFWorldGen.BiomeMithwoodForest")) {
                    mfMithwoodForest = new RealisticBiomeMFMithwoodForest(biome, BiomeConfigMF.biomeConfigMFMithwoodForest);
                }
            }
        }
    }
}
