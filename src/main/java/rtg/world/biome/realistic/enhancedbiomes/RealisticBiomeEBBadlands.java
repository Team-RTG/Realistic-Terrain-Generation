package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBadlands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBadlands;

public class RealisticBiomeEBBadlands extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBBadlands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBBadlands(),
			new SurfaceEBBadlands(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, ebBiome.topBlock, 0.15f)
		);
		
		this.setRealisticBiomeName("EB Badlands");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBBadlands;
	}
}
