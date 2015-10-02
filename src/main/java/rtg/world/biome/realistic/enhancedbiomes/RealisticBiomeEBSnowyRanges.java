package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSnowyRanges;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSnowyRanges;

public class RealisticBiomeEBSnowyRanges extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSnowyRanges(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBSnowyRanges(),
			new SurfaceEBSnowyRanges(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Snowy Ranges");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBSnowyRanges);
	}
}
