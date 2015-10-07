package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBScrub;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBScrub;

public class RealisticBiomeEBScrub extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBScrub(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
			new TerrainEBScrub(false, 35f, 80f, 30f, 20f, 10),
			new SurfaceEBScrub(ebBiome.topBlock, ebBiome.fillerBlock, (byte)0, 20)
		);
		
		this.setRealisticBiomeName("EB Scrub");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBScrub;
	}
}
