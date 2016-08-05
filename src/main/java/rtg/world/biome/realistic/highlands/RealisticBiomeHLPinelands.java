package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLPinelands;
import rtg.world.gen.terrain.highlands.TerrainHLPinelands;

public class RealisticBiomeHLPinelands extends RealisticBiomeHLBase {

    public RealisticBiomeHLPinelands(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLPinelands(),
            new SurfaceHLPinelands(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
