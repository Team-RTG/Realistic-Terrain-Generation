package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBorealForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBorealForest;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPBorealForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.boreal_forest.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPBorealForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBorealForest(),
			new SurfaceBOPBorealForest(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
		);
	}
}
