package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLLake;
import rtg.world.gen.terrain.highlands.TerrainHLLake;

public class RealisticBiomeHLLake extends RealisticBiomeHLBase {

    public RealisticBiomeHLLake(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLLake(),
            new SurfaceHLLake(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
