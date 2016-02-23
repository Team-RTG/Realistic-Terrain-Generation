package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBambooForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBambooForest;

public class RealisticBiomeBOPBambooForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.bamboo_forest.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPBambooForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBambooForest(),
			new SurfaceBOPBambooForest(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
		);
	}
}
