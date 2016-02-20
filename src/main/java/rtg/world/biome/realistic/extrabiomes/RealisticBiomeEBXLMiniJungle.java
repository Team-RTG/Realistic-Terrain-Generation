package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMiniJungle;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMiniJungle;

public class RealisticBiomeEBXLMiniJungle extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.minijungle.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLMiniJungle(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLMiniJungle(0f, 81f, 68f, 200f),
			new SurfaceEBXLMiniJungle(config, topBlock, fillerBlock)
		);
	}
}
