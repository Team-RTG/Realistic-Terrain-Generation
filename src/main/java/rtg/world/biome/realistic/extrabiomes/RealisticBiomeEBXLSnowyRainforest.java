package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLSnowyRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLSnowyRainforest;

public class RealisticBiomeEBXLSnowyRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.snowyrainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLSnowyRainforest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.frozenRiver,
			new TerrainEBXLSnowyRainforest(300f, 70f, 0f),
			new SurfaceEBXLSnowyRainforest(config, topBlock, fillerBlock)
		);
		
		ebxlBiome.setTemperatureRainfall(-2f, ebxlBiome.rainfall);
	}
}
