package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBOakForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBOakForest;

public class RealisticBiomeEBOakForest extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBOakForest(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBOakForest(),
			new SurfaceEBOakForest(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("EB Oak Forest");
		this.biomeWeight = ConfigEB.weightEBOakForest;
	}
}
