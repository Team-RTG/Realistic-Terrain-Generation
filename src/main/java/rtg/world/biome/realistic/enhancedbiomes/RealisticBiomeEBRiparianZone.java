package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRiparianZone;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRiparianZone;

public class RealisticBiomeEBRiparianZone extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBRiparianZone(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBRiparianZone(),
			new SurfaceEBRiparianZone(ebBiome.topBlock, ebBiome.fillerBlock, ebBiome.topBlock, ebBiome.topBlock)
		);
		
		this.setRealisticBiomeName("EB Riparian Zone");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBRiparianZone);
	}
}
