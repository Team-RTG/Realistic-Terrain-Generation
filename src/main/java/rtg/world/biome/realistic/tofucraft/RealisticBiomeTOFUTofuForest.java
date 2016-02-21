package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuForest;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuForest extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuForest(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuForest(),
			new SurfaceTOFUTofuForest(config, tofuBiome.topBlock, tofuBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, tofuBiome.topBlock, (byte)0, 0.10f)
		);
	}
}
