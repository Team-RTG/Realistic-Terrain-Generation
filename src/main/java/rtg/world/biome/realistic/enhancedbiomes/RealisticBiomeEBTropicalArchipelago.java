package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBTropicalArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBTropicalArchipelago;

public class RealisticBiomeEBTropicalArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBTropicalArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainEBTropicalArchipelago(200f, 100f, 0f),
			new SurfaceEBTropicalArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Tropical Archipelago");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBTropicalArchipelago;
	}
}
