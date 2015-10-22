package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBorealPlateauM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBorealPlateauM;

public class RealisticBiomeEBBorealPlateauM extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBBorealPlateauM(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBBorealPlateauM(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceEBBorealPlateauM(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Boreal Plateau M");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEB.weightEBBorealPlateauM;
	}
}
