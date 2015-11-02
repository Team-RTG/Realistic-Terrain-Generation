package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSteppe;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSteppe;

public class RealisticBiomeEBSteppe extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSteppe(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBSteppe(),
			new SurfaceEBSteppe(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Steppe");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSteppe;
	}
}
