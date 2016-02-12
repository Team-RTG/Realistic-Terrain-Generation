package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSteppe;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSteppe;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPSteppe extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.steppe;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSteppe(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSteppe(65f, 68f, 30f),
			new SurfaceBOPSteppe(config, topBlock, fillerBlock)
		);
	}
}
