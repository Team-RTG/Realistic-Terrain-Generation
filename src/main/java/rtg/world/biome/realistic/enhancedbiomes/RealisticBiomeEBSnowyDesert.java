package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSnowyDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSnowyDesert;

public class RealisticBiomeEBSnowyDesert extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSnowyDesert(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBSnowyDesert(),
			new SurfaceEBSnowyDesert(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Snowy Desert");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigEB.weightEBSnowyDesert;
	}
}
