package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLBog;
import rtg.world.gen.terrain.highlands.TerrainHLBog;

public class RealisticBiomeHLBog extends RealisticBiomeHLBase
{
    
    public RealisticBiomeHLBog(BiomeConfig config)
    {
        super(config,
        	HighlandsBiomes.bog, BiomeGenBase.river,
            new TerrainHLBog(),
            new SurfaceHLBog(config, HighlandsBiomes.bog.topBlock, HighlandsBiomes.bog.fillerBlock));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.maxY = 72;
		this.addDeco(decoBaseBiomeDecorations);
    }
  
}
