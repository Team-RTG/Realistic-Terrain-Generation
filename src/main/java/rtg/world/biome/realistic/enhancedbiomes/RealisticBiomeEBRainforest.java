package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRainforest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRainforest;

public class RealisticBiomeEBRainforest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBRainforest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
			new TerrainEBRainforest(),
			new SurfaceEBRainforest(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Rainforest");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBRainforest);
	}
}
