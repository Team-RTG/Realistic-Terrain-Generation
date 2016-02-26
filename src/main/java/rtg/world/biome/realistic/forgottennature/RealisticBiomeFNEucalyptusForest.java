package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNEucalyptusForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNEucalyptusForest;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNEucalyptusForest extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNEucalyptusForest(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNEucalyptusForest(),
            new SurfaceFNEucalyptusForest(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f));
    }
}
