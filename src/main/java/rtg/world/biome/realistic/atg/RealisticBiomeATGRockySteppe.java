package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGRockySteppe;
import rtg.world.gen.terrain.atg.TerrainATGRockySteppe;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGRockySteppe extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGRockySteppe(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGRockySteppe(),
			new SurfaceATGRockySteppe(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
	}
}
