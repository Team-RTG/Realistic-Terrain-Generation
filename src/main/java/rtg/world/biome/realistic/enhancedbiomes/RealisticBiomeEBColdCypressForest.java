package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBColdCypressForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBColdCypressForest;

public class RealisticBiomeEBColdCypressForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBColdCypressForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBColdCypressForest(160f, 80f, 60f),
			new SurfaceEBColdCypressForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Cold Cypress Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBColdCypressForest;
	}
}
