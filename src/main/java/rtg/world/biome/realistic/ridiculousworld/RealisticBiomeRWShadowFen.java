package rtg.world.biome.realistic.ridiculousworld;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWShadowFen;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWShadowFen;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeRWShadowFen extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWShadowFen(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.river,
            new TerrainRWShadowFen(),
            new SurfaceRWShadowFen(config, rwBiome.topBlock, rwBiome.fillerBlock));
    }
}
