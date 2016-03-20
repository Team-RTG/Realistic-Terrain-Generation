package rtg.world.biome.realistic.forgottennature;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNCrystalForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNCrystalForest;

public class RealisticBiomeFNCrystalForest extends RealisticBiomeFNBase {

    public RealisticBiomeFNCrystalForest(BiomeGenBase fnBiome, BiomeConfig config) {

        super(config,
                fnBiome, Biomes.river,
                new TerrainFNCrystalForest(),
                new SurfaceFNCrystalForest(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, fnBiome.topBlock, 0.10f));
    }
}
