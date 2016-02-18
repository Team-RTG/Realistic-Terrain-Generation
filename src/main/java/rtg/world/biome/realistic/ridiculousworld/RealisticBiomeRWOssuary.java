package rtg.world.biome.realistic.ridiculousworld;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.arsmagica.SurfaceAMWitchwoodForest;
import rtg.world.gen.terrain.arsmagica.TerrainAMWitchwoodForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeRWOssuary extends RealisticBiomeRWBase
{
    
    public RealisticBiomeRWOssuary(BiomeGenBase amBiome, BiomeConfig config)
    {
    
        super(config,
            amBiome, BiomeGenBase.river,
            new TerrainAMWitchwoodForest(),
            new SurfaceAMWitchwoodForest(config, amBiome.topBlock, amBiome.fillerBlock));
    }
}
