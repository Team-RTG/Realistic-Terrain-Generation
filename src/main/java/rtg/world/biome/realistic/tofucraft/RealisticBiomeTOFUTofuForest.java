package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGRockySteppe;
import rtg.world.gen.terrain.atg.TerrainATGRockySteppe;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuForest extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuForest(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGRockySteppe(),
			new SurfaceATGRockySteppe(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
	}
}
