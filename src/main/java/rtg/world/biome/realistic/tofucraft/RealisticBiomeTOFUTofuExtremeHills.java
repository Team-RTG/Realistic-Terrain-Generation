package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGSnowyGravelBeach;
import rtg.world.gen.terrain.atg.TerrainATGSnowyGravelBeach;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuExtremeHills extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuExtremeHills(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGSnowyGravelBeach(),
			new SurfaceATGSnowyGravelBeach(config, atgBiome.topBlock, atgBiome.fillerBlock, atgBiome.topBlock, atgBiome.fillerBlock, (byte)0, 1)
		);
	}
}
