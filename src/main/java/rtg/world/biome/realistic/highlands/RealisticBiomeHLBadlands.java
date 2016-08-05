package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLBadlands;
import rtg.world.gen.terrain.highlands.TerrainHLBadlands;

public class RealisticBiomeHLBadlands extends RealisticBiomeHLBase {

    public RealisticBiomeHLBadlands(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLBadlands(),
            new SurfaceHLBadlands(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
