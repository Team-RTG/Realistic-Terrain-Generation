package rtg.world.biome.realistic.buildcraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.buildcraft.ConfigBC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.buildcraft.SurfaceBCDesertOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCDesertOilField;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBCDesertOilField extends RealisticBiomeBCBase
{
    
    public RealisticBiomeBCDesertOilField(BiomeGenBase bcBiome, BiomeConfig config)
    {
    
        super(
            bcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainBCDesertOilField(),
            new SurfaceBCDesertOilField(bcBiome.topBlock, bcBiome.fillerBlock));
        
        this.config = config;
    }
}
