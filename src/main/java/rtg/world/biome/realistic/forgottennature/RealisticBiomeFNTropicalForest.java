package rtg.world.biome.realistic.forgottennature;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.config.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNTropicalForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNTropicalForest;

public class RealisticBiomeFNTropicalForest extends RealisticBiomeFNBase {

    public RealisticBiomeFNTropicalForest(BiomeGenBase fnBiome, BiomeConfig config) {

        super(
                fnBiome, Biomes.river,
                new TerrainFNTropicalForest(),
                new SurfaceFNTropicalForest(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, fnBiome.topBlock, 0.10f));
    }
}
