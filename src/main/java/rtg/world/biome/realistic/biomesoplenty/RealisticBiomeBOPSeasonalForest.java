package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSeasonalForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSeasonalForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSeasonalForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.seasonalForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSeasonalForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainBOPSeasonalForest(0f, 140f, 68f, 200f),
			new SurfaceBOPSeasonalForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Seasonal Forest");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigBOP.weightBOPseasonalForest;
	}
}
