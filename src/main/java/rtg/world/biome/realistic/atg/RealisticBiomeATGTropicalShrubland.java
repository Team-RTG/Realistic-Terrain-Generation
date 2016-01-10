package rtg.world.biome.realistic.atg;

import rtg.api.biomes.atg.config.BiomeConfigATGTropicalShrubland;
import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGTropicalShrubland;
import rtg.world.gen.terrain.atg.TerrainATGTropicalShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGTropicalShrubland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGTropicalShrubland(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainATGTropicalShrubland(),
			new SurfaceATGTropicalShrubland(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigATGTropicalShrubland();
		this.biomeWeight = ConfigATG.weightATGTropicalShrubland;
		this.generateVillages = ConfigATG.villageATGTropicalShrubland;
	}
}
