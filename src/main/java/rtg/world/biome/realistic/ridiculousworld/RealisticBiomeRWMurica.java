package rtg.world.biome.realistic.ridiculousworld;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWMurica;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWMurica;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeRWMurica extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWMurica(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.river,
            new TerrainRWMurica(),
            new SurfaceRWMurica(config, rwBiome.topBlock, rwBiome.fillerBlock));
    }
}
