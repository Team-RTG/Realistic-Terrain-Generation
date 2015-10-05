package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWoodlandHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWoodlandHills;

public class RealisticBiomeEBWoodlandHills extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBWoodlandHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainEBWoodlandHills(),
			new SurfaceEBWoodlandHills(ebBiome.topBlock, ebBiome.fillerBlock, ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Woodland Hills");
		this.biomeWeight = ConfigEB.weightEBWoodlandHills;
	}
}
