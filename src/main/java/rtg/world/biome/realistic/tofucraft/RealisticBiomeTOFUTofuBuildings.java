package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGShrubland;
import rtg.world.gen.terrain.atg.TerrainATGShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuBuildings extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuBuildings(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGShrubland(),
			new SurfaceATGShrubland(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
	}
}
