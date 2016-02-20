package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGGravelBeach;
import rtg.world.gen.terrain.atg.TerrainATGGravelBeach;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGGravelBeach extends RealisticBiomeATGBase
{
	public RealisticBiomeATGGravelBeach(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGGravelBeach(),
			new SurfaceATGGravelBeach(config, atgBiome.topBlock.getBlock(), atgBiome.fillerBlock.getBlock(), atgBiome.topBlock.getBlock(), atgBiome.fillerBlock.getBlock(), (byte)0, 1)
		);
	}
}
