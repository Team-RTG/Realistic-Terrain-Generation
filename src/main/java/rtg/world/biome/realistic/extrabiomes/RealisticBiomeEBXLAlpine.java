package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLAlpine;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLAlpine;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLAlpine extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.alpine.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLAlpine(BiomeConfig config)
	{
		super(
			ebxlBiome, BiomeGenBase.frozenRiver,
			new TerrainEBXLAlpine(),
			new SurfaceEBXLAlpine(config, topBlock, fillerBlock, false, null, 0.45f)
		);
		
		this.config = config;
	}
}
