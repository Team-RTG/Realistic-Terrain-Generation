package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.config.atg.ConfigATG;
import rtg.world.gen.surface.atg.SurfaceATGShrubland;
import rtg.world.gen.terrain.atg.TerrainATGShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGShrubland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGShrubland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(
			atgBiome, BiomeGenBase.river,
			new TerrainATGShrubland(),
			new SurfaceATGShrubland(atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.config = config;
	}
}
