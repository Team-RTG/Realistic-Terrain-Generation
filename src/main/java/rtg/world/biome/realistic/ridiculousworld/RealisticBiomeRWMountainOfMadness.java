package rtg.world.biome.realistic.ridiculousworld;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.ridiculousworld.SurfaceRWMountainOfMadness;
import rtg.world.gen.terrain.ridiculousworld.TerrainRWMountainOfMadness;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeRWMountainOfMadness extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWMountainOfMadness(BiomeGenBase rwBiome, BiomeConfig config)
    {
    
        super(config,
            rwBiome, BiomeGenBase.frozenRiver,
            new TerrainRWMountainOfMadness(10f, 120f, 68f, 200f),
            new SurfaceRWMountainOfMadness(config, rwBiome.topBlock, rwBiome.fillerBlock));
    }
}
