package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLForestedHills;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLForestedHills;

public class RealisticBiomeEBXLForestedHills extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.forestedhills.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLForestedHills(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLForestedHills(300f, 100f, 0f),
			new SurfaceEBXLForestedHills(config, topBlock, fillerBlock, false, null, 0.95f)
		);
	}
}
