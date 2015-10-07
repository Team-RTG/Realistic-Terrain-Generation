package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRockyHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRockyHills;

public class RealisticBiomeEBRockyHills extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBRockyHills(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBRockyHills(80f),
			new SurfaceEBRockyHills(ebBiome.topBlock, ebBiome.fillerBlock, 40f, false, false)
		);
		
		this.setRealisticBiomeName("EB Rocky Hills");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBRockyHills;
	}
}
