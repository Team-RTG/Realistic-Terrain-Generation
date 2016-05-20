package rtg.world.biome.realistic.buildcraft;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.buildcraft.SurfaceBCOceanOilField;
import rtg.world.gen.terrain.buildcraft.TerrainBCOceanOilField;

public class RealisticBiomeBCOceanOilField extends RealisticBiomeBCBase
{
    
    public RealisticBiomeBCOceanOilField(BiomeGenBase bcBiome, BiomeConfig config)
    {
    
        super(config, 
            bcBiome, BiomeGenBase.river,
            new TerrainBCOceanOilField(),
            new SurfaceBCOceanOilField(config, Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
