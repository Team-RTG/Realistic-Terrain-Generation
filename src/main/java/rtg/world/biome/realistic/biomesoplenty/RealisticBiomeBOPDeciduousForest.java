package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeciduousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeciduousForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPDeciduousForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.deciduousForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPDeciduousForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPDeciduousForest(0f, 140f, 68f, 200f),
			new SurfaceBOPDeciduousForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Deciduous Forest");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPdeciduousForest;
	}
}
