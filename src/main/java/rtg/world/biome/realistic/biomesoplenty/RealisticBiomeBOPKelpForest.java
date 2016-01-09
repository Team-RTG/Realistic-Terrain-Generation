package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPKelpForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPKelpForest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPKelpForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.kelpForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPKelpForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPKelpForest(false, -25f, 0f, 0f, 0f, 30f),
			new SurfaceBOPKelpForest(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("BOP Kelp Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPKelpForest;
		this.generateVillages = ConfigBOP.villageBOPKelpForest;
	}
}
