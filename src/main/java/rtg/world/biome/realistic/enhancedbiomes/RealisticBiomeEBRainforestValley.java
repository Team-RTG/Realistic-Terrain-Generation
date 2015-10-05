package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRainforestValley;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRainforestValley;

public class RealisticBiomeEBRainforestValley extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBRainforestValley(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainEBRainforestValley(),
			new SurfaceEBRainforestValley(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Rainforest Valley");
		this.biomeWeight = ConfigEB.weightEBRainforestValley;
	}
}
