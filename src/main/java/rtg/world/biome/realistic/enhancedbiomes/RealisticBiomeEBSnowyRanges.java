package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSnowyRanges;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSnowyRanges;

public class RealisticBiomeEBSnowyRanges extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSnowyRanges(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBSnowyRanges(),
			new SurfaceEBSnowyRanges(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Snowy Ranges");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSnowyRanges;
	}
}
