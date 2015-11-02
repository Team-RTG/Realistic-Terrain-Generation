package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAspenForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAspenForest;

public class RealisticBiomeEBAspenForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAspenForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAspenForest(230f, 120f, 0f),
			new SurfaceEBAspenForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Aspen Forest");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBAspenForest;
	}
}
