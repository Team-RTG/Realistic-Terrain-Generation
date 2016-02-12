package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMountainRidge;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMountainRidge;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLMountainRidge extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.mountainridge.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMountainRidge(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLMountainRidge(230f, 110f, 0f),
			new SurfaceEBXLMountainRidge(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f)
		);
		
		this.config = config;
		this.generatesEmeralds = true;
	}
}
