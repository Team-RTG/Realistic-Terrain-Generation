package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSnowyWastelands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSnowyWastelands;

public class RealisticBiomeEBSnowyWastelands extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSnowyWastelands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBSnowyWastelands(),
			new SurfaceEBSnowyWastelands(Blocks.snow, Blocks.snow)
		);
		
		this.setRealisticBiomeName("EB Snowy Wastelands");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBSnowyWastelands);
	}
}
