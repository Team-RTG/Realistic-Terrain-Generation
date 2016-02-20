package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLTemperateRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLTemperateRainforest;

public class RealisticBiomeEBXLTemperateRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.temperaterainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLTemperateRainforest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLTemperateRainforest(300f, 70f, 0f),
			new SurfaceEBXLTemperateRainforest(config, topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
	}
}
