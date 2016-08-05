package rtg.world.biome.realistic.highlands;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLDryForest;
import rtg.world.gen.terrain.highlands.TerrainHLDryForest;

public class RealisticBiomeHLDryForest extends RealisticBiomeHLBase {

    public RealisticBiomeHLDryForest(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLDryForest(),
            new SurfaceHLDryForest(config, biome.topBlock, biome.fillerBlock)
        );
    }
}
