package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRedwoodForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPRedwoodForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPRedwoodForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.redwoodForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPRedwoodForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainBOPRedwoodForest(80f, 180f, 13f, 100f, 38f, 260f, 71f),
			new SurfaceBOPRedwoodForest(topBlock, fillerBlock, false, null, 0.4f)
		);
		
		this.setRealisticBiomeName("BOP Redwood Forest");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigBOP.weightBOPredwoodForest;
	}
}
