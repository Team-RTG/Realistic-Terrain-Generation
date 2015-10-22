package rtg.world.biome.realistic.buildcraft;

import rtg.config.buildcraft.ConfigBC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.buildcraft.SurfaceBCDesertOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCDesertOilField;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBCDesertOilField extends RealisticBiomeBCBase
{
    
    public RealisticBiomeBCDesertOilField(BiomeGenBase bcBiome)
    {
    
        super(
            bcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainBCDesertOilField(),
            new SurfaceBCDesertOilField(bcBiome.topBlock, bcBiome.fillerBlock));
        
        this.setRealisticBiomeName("BC Desert Oil Field");
        this.biomeCategory = BiomeCategory.HOT;
        this.biomeWeight = ConfigBC.weightBCDesertOilField;
    }
}
