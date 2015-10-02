package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBShrublands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBShrublands;

public class RealisticBiomeEBShrublands extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBShrublands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBShrublands(),
			new SurfaceEBShrublands(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Shrublands");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBShrublands);
	}
}
