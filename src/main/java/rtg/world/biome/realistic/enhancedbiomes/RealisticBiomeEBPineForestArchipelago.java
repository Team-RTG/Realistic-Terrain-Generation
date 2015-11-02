package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBPineForestArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBPineForestArchipelago;

public class RealisticBiomeEBPineForestArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBPineForestArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBPineForestArchipelago(200f, 100f, 0f),
			new SurfaceEBPineForestArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Pine Forest Archipelago");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBPineForestArchipelago;
	}
}
