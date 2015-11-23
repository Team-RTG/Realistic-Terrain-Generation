package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBGlacier;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBGlacier;

public class RealisticBiomeEBGlacier extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBGlacier(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBGlacier(),
			new SurfaceEBGlacier(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Glacier");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBGlacier;
		this.generateVillages = ConfigEB.villageEBGlacier;
	}
}
