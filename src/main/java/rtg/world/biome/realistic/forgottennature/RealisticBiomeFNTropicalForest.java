package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNTropicalForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNTropicalForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNTropicalForest extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNTropicalForest(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNTropicalForest(),
            new SurfaceFNTropicalForest(config, fnBiome.topBlock, fnBiome.fillerBlock));
    }
}
