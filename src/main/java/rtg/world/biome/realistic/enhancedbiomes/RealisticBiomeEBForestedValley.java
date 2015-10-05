package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBForestedValley;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBForestedValley;

public class RealisticBiomeEBForestedValley extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBForestedValley(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBForestedValley(40f),
			new SurfaceEBForestedValley(ebBiome.topBlock, ebBiome.fillerBlock, 20f, false, false)
		);
		
		this.setRealisticBiomeName("EB Forested Valley");
		this.biomeWeight = ConfigEB.weightEBForestedValley;
	}
}
