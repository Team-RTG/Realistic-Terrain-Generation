package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBambooForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBambooForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPBambooForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.bambooForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBambooForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainBOPBambooForest(135f, 300f),
			new SurfaceBOPBambooForest(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("BOP Bamboo Forest");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPbambooForest;
	}
}
