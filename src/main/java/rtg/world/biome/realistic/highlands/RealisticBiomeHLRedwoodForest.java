package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLRedwoodForest;
import rtg.world.gen.terrain.highlands.TerrainHLRedwoodForest;

public class RealisticBiomeHLRedwoodForest extends RealisticBiomeHLBase {

    public RealisticBiomeHLRedwoodForest(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLRedwoodForest(),
            new SurfaceHLRedwoodForest(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
