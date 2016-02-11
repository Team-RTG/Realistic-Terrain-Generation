package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGTropicalShrubland;
import rtg.world.gen.terrain.atg.TerrainATGTropicalShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGTropicalShrubland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGTropicalShrubland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(
			atgBiome, BiomeGenBase.river,
			new TerrainATGTropicalShrubland(),
			new SurfaceATGTropicalShrubland(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
		
		this.config = config;
	}
}
