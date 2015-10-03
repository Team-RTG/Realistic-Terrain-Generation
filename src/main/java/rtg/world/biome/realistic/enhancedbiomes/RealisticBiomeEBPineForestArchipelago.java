package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBPineForestArchipelago;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBPineForestArchipelago;

public class RealisticBiomeEBPineForestArchipelago extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBPineForestArchipelago(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainEBPineForestArchipelago(200f, 100f, 0f),
			new SurfaceEBPineForestArchipelago(ebBiome.topBlock, ebBiome.fillerBlock, false, null, 0.95f)
		);
		
		this.setRealisticBiomeName("EB Pine Forest Archipelago");
		BiomeGenManager.addFrozenBiome(this, ConfigEB.weightEBPineForestArchipelago);
	}
}
