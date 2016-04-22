package rtg.world.biome.realistic.atg;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.atg.SurfaceATGSnowyGravelBeach;
import rtg.world.gen.terrain.atg.TerrainATGSnowyGravelBeach;

public class RealisticBiomeATGSnowyGravelBeach extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGSnowyGravelBeach(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGSnowyGravelBeach(),
			new SurfaceATGSnowyGravelBeach(config, atgBiome.topBlock, atgBiome.fillerBlock, atgBiome.topBlock, atgBiome.fillerBlock, (byte)0, 1)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
