package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLGreenSwamp;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLGreenSwamp;

public class RealisticBiomeEBXLGreenSwamp extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.greenswamp.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLGreenSwamp(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLGreenSwamp(),
			new SurfaceEBXLGreenSwamp(config, topBlock, fillerBlock)
		);
	}
}
