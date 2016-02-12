package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMountainDesert;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMountainDesert;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLMountainDesert extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.mountaindesert.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMountainDesert(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLMountainDesert(230f, 100f, 0f),
			new SurfaceEBXLMountainDesert(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f)
		);
	}
}
