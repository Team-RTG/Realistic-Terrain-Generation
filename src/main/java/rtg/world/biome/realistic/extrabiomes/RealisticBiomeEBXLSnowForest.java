package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biomes.BiomeConfig;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
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
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBXLSnowForest(),
			new SurfaceEBXLSnowForest(topBlock, fillerBlock)
		);
		
		ebxlBiome.setTemperatureRainfall(-2f, ebxlBiome.rainfall);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigEBXL.weightEBXLSnowForest;
		this.generateVillages = ConfigEBXL.villageEBXLSnowForest;
	}
}
