package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.config.atg.ConfigATG;
import rtg.world.gen.surface.atg.SurfaceATGWoodland;
import rtg.world.gen.terrain.atg.TerrainATGWoodland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGWoodland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGWoodland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(
			atgBiome, BiomeGenBase.river,
			new TerrainATGWoodland(),
			new SurfaceATGWoodland(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.config = config;
	}
}
