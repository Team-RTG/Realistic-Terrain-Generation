package rtg.world.biome.realistic.atg;

import rtg.api.biomes.atg.config.BiomeConfigATGSnowyGravelBeach;
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
			new TerrainATGSnowyGravelBeach(),
			new SurfaceATGSnowyGravelBeach(atgBiome.topBlock, atgBiome.fillerBlock, atgBiome.topBlock, atgBiome.fillerBlock, (byte)0, 1)
		);
		
		this.biomeConfig = new BiomeConfigATGSnowyGravelBeach();
		this.biomeWeight = ConfigATG.weightATGSnowyGravelBeach;
		this.generateVillages = ConfigATG.villageATGSnowyGravelBeach;
	}
}
