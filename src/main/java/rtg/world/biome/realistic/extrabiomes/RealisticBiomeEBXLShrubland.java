package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLShrubland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLShrubland;

public class RealisticBiomeEBXLShrubland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.shrubland.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLShrubland(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLShrubland(58f,80f,30f),
			new SurfaceEBXLShrubland(config, topBlock, fillerBlock)
		);
	}
}
