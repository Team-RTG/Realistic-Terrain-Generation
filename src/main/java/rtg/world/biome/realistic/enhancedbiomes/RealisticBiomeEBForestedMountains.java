package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBForestedMountains;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBForestedMountains;

public class RealisticBiomeEBForestedMountains extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBForestedMountains(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBForestedMountains(120f, 70f, 60f),
			new SurfaceEBForestedMountains(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Forested Mountains");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigEB.weightEBForestedMountains;
	}
}
