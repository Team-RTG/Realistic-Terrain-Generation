package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLPineForest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLPineForest;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLPineForest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.pineforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLPineForest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLPineForest(68f, 85f, 15),
			new SurfaceEBXLPineForest(config, topBlock, fillerBlock, false, null, 1.2f)
		);
	}
}
