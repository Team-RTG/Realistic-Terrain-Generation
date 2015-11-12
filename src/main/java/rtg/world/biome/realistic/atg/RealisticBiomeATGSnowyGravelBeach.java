package rtg.world.biome.realistic.atg;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGSnowyGravelBeach;
import rtg.world.gen.terrain.atg.TerrainATGSnowyGravelBeach;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGSnowyGravelBeach extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGSnowyGravelBeach(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainATGSnowyGravelBeach(230f, 120f, 50f),
			new SurfaceATGSnowyGravelBeach(atgBiome.topBlock, atgBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("ATG Snowy Gravel Beach");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigATG.weightATGSnowyGravelBeach;
	}
}
