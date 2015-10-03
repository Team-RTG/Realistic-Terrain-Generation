package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRockyDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRockyDesert;

public class RealisticBiomeEBRockyDesert extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBRockyDesert(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.HOT),
			new TerrainEBRockyDesert(40f),
			new SurfaceEBRockyDesert(ebBiome.topBlock, ebBiome.fillerBlock, 20f, false, false)
		);
		
		this.setRealisticBiomeName("EB Rocky Desert");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBRockyDesert);
	}
}
