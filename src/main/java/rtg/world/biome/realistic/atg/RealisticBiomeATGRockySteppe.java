package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGRockySteppe;
import rtg.world.gen.terrain.atg.TerrainATGRockySteppe;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGRockySteppe extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGRockySteppe(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainATGRockySteppe(),
			new SurfaceATGRockySteppe(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.config = config;
		this.generateVillages = ConfigATG.villageATGGravelBeach;
	}
}
