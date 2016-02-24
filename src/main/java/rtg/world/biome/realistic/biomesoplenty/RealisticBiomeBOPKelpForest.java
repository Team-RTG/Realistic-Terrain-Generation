package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPKelpForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPKelpForest;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPKelpForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.kelp_forest.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPKelpForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPKelpForest(false, -10f, 0f, 0f, 0f, 30f),
			new SurfaceBOPKelpForest(config, topBlock, fillerBlock)
		);
	}
}
