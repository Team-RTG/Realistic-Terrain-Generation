package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSilverPineForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSilverPineForest;

public class RealisticBiomeEBSilverPineForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSilverPineForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBSilverPineForest(100f, 70f, 0f),
			new SurfaceEBSilverPineForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Silver Pine Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSilverPineForest;
	}
}
