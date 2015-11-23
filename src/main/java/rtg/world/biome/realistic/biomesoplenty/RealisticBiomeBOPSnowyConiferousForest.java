package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSnowyConiferousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSnowyConiferousForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSnowyConiferousForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.snowyConiferousForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSnowyConiferousForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainBOPSnowyConiferousForest(),
			new SurfaceBOPSnowyConiferousForest(topBlock, fillerBlock, false, null, 0.45f, 1.5f, 50f, 60f, 0.4f, 100f, 50f, 1.5f)
		);
		
		this.setRealisticBiomeName("BOP Snowy Coniferous Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPsnowyConiferousForest;
		this.generateVillages = ConfigBOP.villageBOPsnowyConiferousForest;
	}
}
