package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGTundra;
import rtg.world.gen.terrain.atg.TerrainATGTundra;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuForestHills extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuForestHills(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGTundra(),
			new SurfaceATGTundra(config, atgBiome.topBlock, atgBiome.fillerBlock, false, null, 0.45f)
		);
	}
}
