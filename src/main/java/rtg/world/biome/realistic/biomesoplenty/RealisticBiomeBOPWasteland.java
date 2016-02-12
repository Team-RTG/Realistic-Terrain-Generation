package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWasteland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWasteland;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPWasteland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.wasteland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPWasteland(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPWasteland(),
			new SurfaceBOPWasteland(config, topBlock, fillerBlock)
		);
	}
}
