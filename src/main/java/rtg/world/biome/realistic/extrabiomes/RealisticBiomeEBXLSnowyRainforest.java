package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLSnowyRainforest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLSnowyRainforest;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLSnowyRainforest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.snowyrainforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLSnowyRainforest(BiomeConfig config)
	{
		super(
			ebxlBiome, BiomeGenBase.frozenRiver,
			new TerrainEBXLSnowyRainforest(300f, 70f, 0f),
			new SurfaceEBXLSnowyRainforest(topBlock, fillerBlock)
		);
		
		ebxlBiome.setTemperatureRainfall(-2f, ebxlBiome.rainfall);
		
		this.config = config;
	}
}
