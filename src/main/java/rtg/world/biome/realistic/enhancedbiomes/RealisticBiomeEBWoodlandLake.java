package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWoodlandLake;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWoodlandLake;

public class RealisticBiomeEBWoodlandLake extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBWoodlandLake(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBWoodlandLake(),
			new SurfaceEBWoodlandLake(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.grass, Blocks.grass)
		);
		
		this.setRealisticBiomeName("EB Woodland Lake");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBWoodlandLake;
	}
}
