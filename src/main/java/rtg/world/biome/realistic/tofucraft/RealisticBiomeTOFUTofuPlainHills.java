package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuPlainHills;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuPlainHills;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuPlainHills extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuPlainHills(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuPlainHills(),
			new SurfaceTOFUTofuPlainHills(config, tofuBiome.topBlock, tofuBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, tofuBiome.topBlock, (byte)0, 0.15f)
		);
	}
}
