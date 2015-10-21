package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBColdFirForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBColdFirForest;

public class RealisticBiomeEBColdFirForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBColdFirForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBColdFirForest(160f, 80f, 60f),
			new SurfaceEBColdFirForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Cold Fir Forest");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigEB.weightEBColdFirForest;
	}
}
