package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMountainRidge;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMountainRidge;

public class RealisticBiomeEBXLMountainRidge extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.mountainridge.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLMountainRidge(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLMountainRidge(230f, 110f, 0f),
			new SurfaceEBXLMountainRidge(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f)
		);
		this.generatesEmeralds = true;
	}
}
