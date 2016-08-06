package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLTropicalIslands;
import rtg.world.gen.terrain.highlands.TerrainHLTropicalIslands;

public class RealisticBiomeHLTropicalIslands extends RealisticBiomeHLBase {

    public RealisticBiomeHLTropicalIslands(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLTropicalIslands(90f, 180f, 13f, 100f, 1f, 260f, 59f),
            new SurfaceHLTropicalIslands(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
