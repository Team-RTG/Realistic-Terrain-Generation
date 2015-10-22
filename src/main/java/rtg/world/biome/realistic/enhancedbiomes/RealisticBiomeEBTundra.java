package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBTundra;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBTundra;

public class RealisticBiomeEBTundra extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBTundra(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBTundra(),
			new SurfaceEBTundra(ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Tundra");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEB.weightEBTundra;
	}
}
