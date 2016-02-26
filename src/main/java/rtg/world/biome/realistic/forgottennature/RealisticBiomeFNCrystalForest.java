package rtg.world.biome.realistic.forgottennature;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.forgottennature.SurfaceFNCrystalForest;
import rtg.world.gen.terrain.forgottennature.TerrainFNCrystalForest;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeFNCrystalForest extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNCrystalForest(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNCrystalForest(),
            new SurfaceFNCrystalForest(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, fnBiome.topBlock, (byte)0, 0.10f));
    }
}
