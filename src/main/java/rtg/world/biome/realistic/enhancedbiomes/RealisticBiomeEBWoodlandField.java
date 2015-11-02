package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWoodlandField;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWoodlandField;

public class RealisticBiomeEBWoodlandField extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBWoodlandField(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBWoodlandField(),
			new SurfaceEBWoodlandField(ebBiome.topBlock, ebBiome.fillerBlock, ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Woodland Field");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBWoodlandField;
	}
}
