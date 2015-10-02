package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRedDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRedDesert;

public class RealisticBiomeEBRedDesert extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBRedDesert(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainEBRedDesert(),
			new SurfaceEBRedDesert(ebBiome.topBlock, ebBiome.fillerBlock, Blocks.sand, Blocks.sand, Blocks.sand)
		);
		
		this.setRealisticBiomeName("EB Red Desert");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBRedDesert);
	}
}
