package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBorealPlateau;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBorealPlateau;

public class RealisticBiomeEBBorealPlateau extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBBorealPlateau(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBBorealPlateau(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceEBBorealPlateau(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Boreal Plateau");
		this.biomeCategory = BiomeCategory.NORMAL;
		this.biomeWeight = ConfigEB.weightEBBorealPlateau;
	}
}
