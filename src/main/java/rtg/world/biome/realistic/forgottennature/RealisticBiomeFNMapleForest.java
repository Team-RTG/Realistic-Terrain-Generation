package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNMapleForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNMapleForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNMapleForest extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNMapleForest(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNMapleForest(),
            new SurfaceFNMapleForest(config, fnBiome.topBlock, fnBiome.fillerBlock));
    }
}
