package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBTropicalArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBTropicalArchipelago;

public class RealisticBiomeEBTropicalArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBTropicalArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBTropicalArchipelago(200f, 100f, 0f),
			new SurfaceEBTropicalArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Tropical Archipelago");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBTropicalArchipelago;
		this.generateVillages = ConfigEB.villageEBTropicalArchipelago;
	}
}
