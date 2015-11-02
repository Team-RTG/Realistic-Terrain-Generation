package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBFrozenArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBFrozenArchipelago;

public class RealisticBiomeEBFrozenArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBFrozenArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBFrozenArchipelago(),
			new SurfaceEBFrozenArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, ebBiome.topBlock, ebBiome.fillerBlock)
		);
		
		this.setRealisticBiomeName("EB Frozen Archipelago");
		this.biomeCategory = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBFrozenArchipelago;
	}
}
