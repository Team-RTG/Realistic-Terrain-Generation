package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAspenHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAspenHills;

public class RealisticBiomeEBAspenHills extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAspenHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAspenHills(230f, 120f, 0f),
			new SurfaceEBAspenHills(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Aspen Hills");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBAspenHills;
		this.generateVillages = ConfigEB.villageEBAspenHills;
	}
}
