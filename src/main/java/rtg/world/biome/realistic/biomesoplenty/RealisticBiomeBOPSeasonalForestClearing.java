package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSeasonalForestClearing;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSeasonalForestClearing;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSeasonalForestClearing extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.seasonalForestClearing;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSeasonalForestClearing()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPSeasonalForestClearing(0f, 140f, 68f, 170f),
			new SurfaceBOPSeasonalForestClearing(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Seasonal Forest Clearing");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPseasonalForestClearing;
	}
}
