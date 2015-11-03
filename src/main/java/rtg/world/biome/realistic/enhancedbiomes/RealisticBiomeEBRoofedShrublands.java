package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRoofedShrublands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRoofedShrublands;

public class RealisticBiomeEBRoofedShrublands extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBRoofedShrublands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBRoofedShrublands(),
			new SurfaceEBRoofedShrublands(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Roofed Shrublands");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBRoofedShrublands;
	}
}
