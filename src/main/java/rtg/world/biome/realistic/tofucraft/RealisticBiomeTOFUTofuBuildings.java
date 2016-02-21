package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuBuildings;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuBuildings;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuBuildings extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuBuildings(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuBuildings(),
			new SurfaceTOFUTofuBuildings(config, tofuBiome.topBlock, tofuBiome.fillerBlock)
		);
	}
}
