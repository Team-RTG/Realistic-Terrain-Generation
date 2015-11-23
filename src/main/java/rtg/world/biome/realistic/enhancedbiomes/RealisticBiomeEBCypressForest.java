package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBCypressForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBCypressForest;

public class RealisticBiomeEBCypressForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBCypressForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBCypressForest(),
			new SurfaceEBCypressForest(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Cypress Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBCypressForest;
		this.generateVillages = ConfigEB.villageEBCypressForest;
	}
}
