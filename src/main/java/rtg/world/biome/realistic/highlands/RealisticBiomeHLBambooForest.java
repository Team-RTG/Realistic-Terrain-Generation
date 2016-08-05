package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLBambooForest;
import rtg.world.gen.terrain.highlands.TerrainHLBambooForest;

public class RealisticBiomeHLBambooForest extends RealisticBiomeHLBase {

    public RealisticBiomeHLBambooForest(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLBambooForest(),
            new SurfaceHLBambooForest(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
