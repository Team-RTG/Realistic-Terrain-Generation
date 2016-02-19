package rtg.world.biome.realistic.ridiculousworld;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWRockCandyMountain;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWRockCandyMountain;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeRWRockCandyMountain extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWRockCandyMountain(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.river,
            new TerrainRWRockCandyMountain(),
            new SurfaceRWRockCandyMountain(config, rwBiome.topBlock, rwBiome.fillerBlock));
    }
}
