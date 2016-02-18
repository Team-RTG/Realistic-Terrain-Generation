package rtg.world.biome.realistic.buildcraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.buildcraft.ConfigBC;
import rtg.world.gen.surface.buildcraft.SurfaceBCDesertOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCDesertOilField;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBCDesertOilField extends RealisticBiomeBCBase
{
    
    public RealisticBiomeBCDesertOilField(BiomeGenBase bcBiome, BiomeConfig config)
    {
    
        super(
            bcBiome, BiomeGenBase.river,
            new TerrainBCDesertOilField(),
            new SurfaceBCDesertOilField(bcBiome.topBlock.getBlock(), bcBiome.fillerBlock.getBlock()));
        
        this.config = config;
    }
}
