package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuRiver;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuRiver;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuRiver extends RealisticBiomeTOFUBase
{
	public RealisticBiomeTOFUTofuRiver(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuRiver(),
			new SurfaceTOFUTofuRiver(config)
		);
	}
}
