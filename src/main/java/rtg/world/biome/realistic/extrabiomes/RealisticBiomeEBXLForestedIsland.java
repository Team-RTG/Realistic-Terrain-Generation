package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLForestedIsland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLForestedIsland;

public class RealisticBiomeEBXLForestedIsland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.forestedisland.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLForestedIsland(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLForestedIsland(31f, 280f, 1f, 100f, 1f, 260f, 59f),
			new SurfaceEBXLForestedIsland(config, topBlock, fillerBlock)
		);
	}
}
