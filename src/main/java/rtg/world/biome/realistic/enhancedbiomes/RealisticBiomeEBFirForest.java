package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBFirForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBFirForest;

public class RealisticBiomeEBFirForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBFirForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBFirForest(160f, 80f, 60f),
			new SurfaceEBFirForest(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Fir Forest");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBFirForest);
	}
}
