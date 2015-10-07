package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWastelands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWastelands;

public class RealisticBiomeEBWastelands extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBWastelands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBWastelands(),
			new SurfaceEBWastelands(ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Wastelands");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBWastelands;
	}
}
