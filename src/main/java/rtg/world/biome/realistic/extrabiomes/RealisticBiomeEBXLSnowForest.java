package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLSnowForest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLSnowForest;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLSnowForest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.snowforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLSnowForest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.frozenRiver,
			new TerrainEBXLSnowForest(58f,110f,25f),
			new SurfaceEBXLSnowForest(config, topBlock, fillerBlock)
		);
		
		ebxlBiome.setTemperatureRainfall(-2f, ebxlBiome.rainfall);
	}
}
