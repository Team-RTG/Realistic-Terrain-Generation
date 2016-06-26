package rtg.world.biome.realistic.minestrappolation;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.minestrappolation.SurfaceMSTheFrost;
import rtg.world.gen.terrain.minestrappolation.TerrainMSTheFrost;

public class RealisticBiomeMSTheFrost extends RealisticBiomeMSBase
{

    public RealisticBiomeMSTheFrost(BiomeGenBase msBiome, BiomeConfig config)
    {
    
        super(config, 
            msBiome,
            BiomeGenBase.river,
            new TerrainMSTheFrost(),
            new SurfaceMSTheFrost(config, msBiome.topBlock, msBiome.fillerBlock));
    }
}
