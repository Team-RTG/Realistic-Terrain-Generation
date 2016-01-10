package rtg.world.biome.realistic.atg;

import rtg.api.biomes.atg.config.BiomeConfigATGWoodland;
import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGWoodland;
import rtg.world.gen.terrain.atg.TerrainATGWoodland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGWoodland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGWoodland(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainATGWoodland(),
			new SurfaceATGWoodland(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigATGWoodland();
		this.biomeWeight = ConfigATG.weightATGWoodland;
		this.generateVillages = ConfigATG.villageATGWoodland;
	}
}
