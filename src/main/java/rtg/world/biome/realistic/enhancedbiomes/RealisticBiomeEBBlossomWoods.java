package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBlossomWoods;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBlossomWoods;

public class RealisticBiomeEBBlossomWoods extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBBlossomWoods(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBBlossomWoods(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceEBBlossomWoods(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Blossom Woods");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBBlossomWoods;
	}
}
