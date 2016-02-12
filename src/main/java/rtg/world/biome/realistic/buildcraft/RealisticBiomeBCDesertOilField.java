package rtg.world.biome.realistic.buildcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.buildcraft.SurfaceBCDesertOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCDesertOilField;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBCDesertOilField extends RealisticBiomeBCBase
{
    
    public RealisticBiomeBCDesertOilField(BiomeGenBase bcBiome, BiomeConfig config)
    {
    
        super(config, 
            bcBiome, BiomeGenBase.river,
            new TerrainBCDesertOilField(),
            new SurfaceBCDesertOilField(config, bcBiome.topBlock, bcBiome.fillerBlock));
        
        this.config = config;
    }
}
