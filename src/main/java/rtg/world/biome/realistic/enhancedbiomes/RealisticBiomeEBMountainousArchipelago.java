package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBMountainousArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBMountainousArchipelago;

public class RealisticBiomeEBMountainousArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBMountainousArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBMountainousArchipelago(200f, 100f, 0f),
			new SurfaceEBMountainousArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Mountainous Archipelago");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigEB.weightEBMountainousArchipelago;
	}
}
