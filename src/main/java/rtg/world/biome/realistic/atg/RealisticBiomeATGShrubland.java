package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGShrubland;
import rtg.world.gen.terrain.atg.TerrainATGShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGShrubland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGShrubland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainATGShrubland(),
			new SurfaceATGShrubland(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigATG.weightATGShrubland;
		this.generateVillages = ConfigATG.villageATGShrubland;
	}
}
