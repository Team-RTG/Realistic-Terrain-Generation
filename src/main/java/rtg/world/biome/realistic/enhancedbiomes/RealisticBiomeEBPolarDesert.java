package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBPolarDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBPolarDesert;

public class RealisticBiomeEBPolarDesert extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBPolarDesert(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBPolarDesert(),
			new SurfaceEBPolarDesert(ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Polar Desert");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBPolarDesert);
	}
}
