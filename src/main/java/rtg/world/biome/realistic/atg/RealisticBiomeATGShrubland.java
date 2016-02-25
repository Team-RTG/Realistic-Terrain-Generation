package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGShrubland;
import rtg.world.gen.terrain.atg.TerrainATGShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGShrubland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGShrubland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGShrubland(),
			new SurfaceATGShrubland(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
	}
}
