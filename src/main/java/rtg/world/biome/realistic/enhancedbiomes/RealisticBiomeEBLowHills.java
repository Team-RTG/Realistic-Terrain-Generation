package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBLowHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBLowHills;

public class RealisticBiomeEBLowHills extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBLowHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBLowHills(),
			new SurfaceEBLowHills(ebBiome.topBlock, ebBiome.fillerBlock, ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Low Hills");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBLowHills);
	}
}
