package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGTropicalShrubland;
import rtg.world.gen.terrain.atg.TerrainATGTropicalShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGTropicalShrubland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGTropicalShrubland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainATGTropicalShrubland(),
			new SurfaceATGTropicalShrubland(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigATG.weightATGTropicalShrubland;
		this.generateVillages = ConfigATG.villageATGTropicalShrubland;
	}
}
