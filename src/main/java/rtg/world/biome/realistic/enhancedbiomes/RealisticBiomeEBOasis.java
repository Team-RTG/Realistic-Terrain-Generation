package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBOasis;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBOasis;

public class RealisticBiomeEBOasis extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBOasis(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBOasis(),
			new SurfaceEBOasis(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Oasis");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEB.weightEBOasis;
	}
}
