package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWoodlandLakeEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWoodlandLakeEdge;

public class RealisticBiomeEBWoodlandLakeEdge extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBWoodlandLakeEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBWoodlandLakeEdge(),
			new SurfaceEBWoodlandLakeEdge(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.grass, Blocks.grass)
		);
		
		this.setRealisticBiomeName("EB Woodland Lake Edge");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEB.weightEBWoodlandLakeEdge;
	}
}
