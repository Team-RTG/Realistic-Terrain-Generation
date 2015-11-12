package rtg.world.biome.realistic.atg;

import rtg.config.atg.ConfigATG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.atg.SurfaceATGGravelBeach;
import rtg.world.gen.terrain.atg.TerrainATGGravelBeach;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGGravelBeach extends RealisticBiomeATGBase
{
	public RealisticBiomeATGGravelBeach(BiomeGenBase atgBiome)
	{
		super(
			atgBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainATGGravelBeach(),
			new SurfaceATGGravelBeach(atgBiome.topBlock, atgBiome.fillerBlock, atgBiome.topBlock, atgBiome.fillerBlock, (byte)0, 1)
		);
		
		this.setRealisticBiomeName("ATG Gravel Beach");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigATG.weightATGGravelBeach;
	}
}
