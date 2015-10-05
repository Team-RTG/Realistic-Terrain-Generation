package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBCypressForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBCypressForest;

public class RealisticBiomeEBCypressForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBCypressForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBCypressForest(),
			new SurfaceEBCypressForest(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Cypress Forest");
		this.biomeWeight = ConfigEB.weightEBCypressForest;
	}
}
