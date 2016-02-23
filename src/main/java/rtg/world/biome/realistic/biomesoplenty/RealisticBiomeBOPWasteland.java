package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWasteland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWasteland;

public class RealisticBiomeBOPWasteland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.wasteland.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPWasteland(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPWasteland(),
			new SurfaceBOPWasteland(config, topBlock, fillerBlock)
		);
	}
}
