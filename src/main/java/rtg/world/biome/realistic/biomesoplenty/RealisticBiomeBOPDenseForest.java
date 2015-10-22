package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDenseForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDenseForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPDenseForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.denseForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPDenseForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPDenseForest(0f, 140f, 68f, 200f),
			new SurfaceBOPDenseForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Dense Forest");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigBOP.weightBOPdenseForest;
	}
}
