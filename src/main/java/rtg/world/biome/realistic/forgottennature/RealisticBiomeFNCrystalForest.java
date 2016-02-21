package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNCrystalForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNCrystalForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNCrystalForest extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNCrystalForest(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNCrystalForest(),
            new SurfaceFNCrystalForest(config, fnBiome.topBlock, fnBiome.fillerBlock));
    }
}
