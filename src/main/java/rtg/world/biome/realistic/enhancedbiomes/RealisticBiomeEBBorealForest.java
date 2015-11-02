package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBorealForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBorealForest;

public class RealisticBiomeEBBorealForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBBorealForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBBorealForest(),
			new SurfaceEBBorealForest(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Boreal Forest");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBBorealForest;
	}
}
