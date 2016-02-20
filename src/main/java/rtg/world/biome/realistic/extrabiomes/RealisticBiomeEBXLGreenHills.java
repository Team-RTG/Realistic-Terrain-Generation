package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLGreenHills;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLGreenHills;

public class RealisticBiomeEBXLGreenHills extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.greenhills.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLGreenHills(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLGreenHills(360f, 100f, 0f),
			new SurfaceEBXLGreenHills(config, topBlock, fillerBlock, false, null, 0.95f)
		);
	}
}
