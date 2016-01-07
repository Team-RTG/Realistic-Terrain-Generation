package rtg.world.biome.realistic.atg;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGRockySteppe;
import rtg.world.gen.terrain.atg.TerrainATGRockySteppe;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGRockySteppe extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGRockySteppe(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainATGRockySteppe(),
			new SurfaceATGRockySteppe(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("ATG Rocky Steppe");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = rtg.config.atg.ConfigATG.weightATGRockySteppe;
		this.generateVillages = ConfigATG.villageATGGravelBeach;
	}
}
