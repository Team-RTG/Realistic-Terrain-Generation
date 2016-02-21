package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGTropicalShrubland;
import rtg.world.gen.terrain.atg.TerrainATGTropicalShrubland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuPlainHills extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuPlainHills(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGTropicalShrubland(),
			new SurfaceATGTropicalShrubland(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
	}
}
