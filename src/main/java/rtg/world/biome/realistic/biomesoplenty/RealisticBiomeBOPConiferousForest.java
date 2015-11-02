package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPConiferousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPConiferousForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPConiferousForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.coniferousForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPConiferousForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainBOPConiferousForest(),
			new SurfaceBOPConiferousForest(topBlock, fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("BOP Coniferous Forest");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPconiferousForest;
	}
}
