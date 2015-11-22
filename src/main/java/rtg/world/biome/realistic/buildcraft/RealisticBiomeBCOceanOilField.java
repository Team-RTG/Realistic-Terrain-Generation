package rtg.world.biome.realistic.buildcraft;

import rtg.config.buildcraft.ConfigBC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.buildcraft.SurfaceBCOceanOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCOceanOilField;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBCOceanOilField extends RealisticBiomeBCBase
{
    
    public RealisticBiomeBCOceanOilField(BiomeGenBase bcBiome)
    {
    
        super(
            bcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainBCOceanOilField(),
            new SurfaceBCOceanOilField(Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.setRealisticBiomeName("BC Ocean Oil Field");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigBC.weightBCOceanOilField;
        this.generateVillages = ConfigBC.villageBCOceanOilField;
    }
}
