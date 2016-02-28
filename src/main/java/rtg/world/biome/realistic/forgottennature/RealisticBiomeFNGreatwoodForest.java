package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNGreatwoodForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNGreatwoodForest;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNGreatwoodForest extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNGreatwoodForest(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNGreatwoodForest(),
            new SurfaceFNGreatwoodForest(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f));
    }
}
