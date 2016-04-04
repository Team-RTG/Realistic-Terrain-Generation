package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLTemperateRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLTemperateRainforest;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLTemperateRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.temperaterainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLTemperateRainforest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLTemperateRainforest(130f, 100f),
			new SurfaceEBXLTemperateRainforest(config, topBlock, fillerBlock, true, Blocks.sand, 0.8f)
		);
	}
}
