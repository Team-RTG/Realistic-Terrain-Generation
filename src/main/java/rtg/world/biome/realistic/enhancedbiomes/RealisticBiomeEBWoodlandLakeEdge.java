package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWoodlandLakeEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWoodlandLakeEdge;

public class RealisticBiomeEBWoodlandLakeEdge extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBWoodlandLakeEdge(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBWoodlandLakeEdge(),
			new SurfaceEBWoodlandLakeEdge(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.grass, Blocks.grass)
		);
		
		this.setRealisticBiomeName("EB Woodland Lake Edge");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBWoodlandLakeEdge);
	}
}
