package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBasin;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBasin;

public class RealisticBiomeEBBasin extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBBasin(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBBasin(),
			new SurfaceEBBasin(ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Basin");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBBasin);
	}
}
