package rtg.world.biome.realistic.mithwoodforest;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.mithwoodforest.config.BiomeConfigMF;
import rtg.util.BiomeUtils;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMFBase extends RealisticBiomeBase {

    public static RealisticBiomeBase mfMithwoodForest;

    public RealisticBiomeMFBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        super(config, b, riverbiome, t, s);

        this.lavaSurfaceLakeChance = 0;
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("mithwoodforest")) {

            Biome[] b = BiomeUtils.getRegisteredBiomes();

            for (int i = 0; i < 256; i++) {

                if (b[i] != null) {

                    if (BiomeUtils.getName(b[i]) == null) {

                        Logger.warn("Biome ID %d has no name.", BiomeUtils.getId(b[i]));
                        continue;
                    }

                    Biome biome = b[i];
                    String biomeName = BiomeUtils.getName(biome);
                    String biomeClass = biome.getBiomeClass().getName();

                    if (biomeName.equals("Mithwood Forest") && biomeClass.equals("rainbeau.mithwoodforest.RMFWorldGen.BiomeMithwoodForest")) {
                        mfMithwoodForest = new RealisticBiomeMFMithwoodForest(biome, BiomeConfigMF.biomeConfigMFMithwoodForest);
                    }
                }
            }
        }
    }
}
