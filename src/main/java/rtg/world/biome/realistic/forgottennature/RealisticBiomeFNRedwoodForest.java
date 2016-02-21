package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNRedwoodForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNRedwoodForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNRedwoodForest extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNRedwoodForest(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNRedwoodForest(),
            new SurfaceFNRedwoodForest(config, fnBiome.topBlock, fnBiome.fillerBlock));
    }
}
