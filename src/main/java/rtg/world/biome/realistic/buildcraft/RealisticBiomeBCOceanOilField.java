package rtg.world.biome.realistic.buildcraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.buildcraft.ConfigBC;
import rtg.world.gen.surface.buildcraft.SurfaceBCOceanOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCOceanOilField;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBCOceanOilField extends RealisticBiomeBCBase
{
    
    public RealisticBiomeBCOceanOilField(BiomeGenBase bcBiome, BiomeConfig config)
    {
    
        super(
            bcBiome, BiomeGenBase.river,
            new TerrainBCOceanOilField(),
            new SurfaceBCOceanOilField(Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.config = config;
    }
}
