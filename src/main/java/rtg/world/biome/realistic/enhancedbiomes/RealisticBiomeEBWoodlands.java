package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWoodlands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWoodlands;

public class RealisticBiomeEBWoodlands extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBWoodlands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBWoodlands(),
			new SurfaceEBWoodlands(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Woodlands");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBWoodlands;
		this.generateVillages = ConfigEB.villageEBWoodlands;
	}
}
