package rtg.world.biome.realistic.ridiculousworld;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWSpookyForest;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWSpookyForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeRWSpookyForest extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWSpookyForest(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.river,
            new TerrainRWSpookyForest(),
            new SurfaceRWSpookyForest(config, rwBiome.topBlock, rwBiome.fillerBlock));
    }
}
