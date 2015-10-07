package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAspenForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAspenForest;

public class RealisticBiomeEBAspenForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAspenForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBAspenForest(230f, 120f, 0f),
			new SurfaceEBAspenForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Aspen Forest");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEB.weightEBAspenForest;
	}
}
