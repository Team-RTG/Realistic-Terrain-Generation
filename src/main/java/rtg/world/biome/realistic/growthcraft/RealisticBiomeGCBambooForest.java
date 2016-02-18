package rtg.world.biome.realistic.growthcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.growthcraft.SurfaceGCBambooForest;
import rtg.world.gen.terrain.growthcraft.TerrainGCBambooForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeGCBambooForest extends RealisticBiomeGCBase
{
    
    public RealisticBiomeGCBambooForest(BiomeGenBase gcBiome, BiomeConfig config)
    {
    
        super(config,
            gcBiome, BiomeGenBase.river,
            new TerrainGCBambooForest(),
            new SurfaceGCBambooForest(config, gcBiome.topBlock, gcBiome.fillerBlock));
    }
}
