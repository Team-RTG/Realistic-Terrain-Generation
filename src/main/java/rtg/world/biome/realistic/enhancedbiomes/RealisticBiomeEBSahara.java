package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSahara;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSahara;

public class RealisticBiomeEBSahara extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSahara(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBSahara(),
			new SurfaceEBSahara(ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Sahara");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSahara;
		this.generateVillages = ConfigEB.villageEBSahara;
	}
}
