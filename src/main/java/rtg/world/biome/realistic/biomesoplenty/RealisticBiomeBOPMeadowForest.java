package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMeadowForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMeadowForest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMeadowForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.meadowForest;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPMeadowForest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMeadowForest(),
			new SurfaceBOPMeadowForest(topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
