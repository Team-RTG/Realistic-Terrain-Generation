package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLLowlands;
import rtg.world.gen.terrain.highlands.TerrainHLLowlands;

public class RealisticBiomeHLLowlands extends RealisticBiomeHLBase {

    public RealisticBiomeHLLowlands(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLLowlands(),
            new SurfaceHLLowlands(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
