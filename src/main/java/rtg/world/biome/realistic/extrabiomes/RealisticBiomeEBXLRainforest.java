package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLRainforest;

public class RealisticBiomeEBXLRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.rainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLRainforest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLRainforest(230f, 43f, 0f),
			new SurfaceEBXLRainforest(config, topBlock, fillerBlock)
		);
	}
}
