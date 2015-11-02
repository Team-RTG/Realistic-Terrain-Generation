package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBKakadu;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBKakadu;

public class RealisticBiomeEBKakadu extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBKakadu(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBKakadu(),
			new SurfaceEBKakadu(ebBiome.topBlock, ebBiome.fillerBlock, ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Kakadu");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBKakadu;
	}
}
