package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBColdBorealForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBColdBorealForest;

public class RealisticBiomeEBColdBorealForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBColdBorealForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBColdBorealForest(160f, 80f, 60f),
			new SurfaceEBColdBorealForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Cold Boreal Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBColdBorealForest;
	}
}
