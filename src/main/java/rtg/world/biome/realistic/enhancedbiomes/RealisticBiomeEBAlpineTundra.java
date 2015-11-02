package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBAlpineTundra;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBAlpineTundra;

public class RealisticBiomeEBAlpineTundra extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBAlpineTundra(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBAlpineTundra(),
			new SurfaceEBAlpineTundra(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EB Alpine Tundra");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBAlpineTundra;
	}
}
