package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuPlains;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuPlains;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuPlains extends RealisticBiomeTOFUBase
{
	public RealisticBiomeTOFUTofuPlains(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuPlains(),
			new SurfaceTOFUTofuPlains(config, tofuBiome.topBlock, tofuBiome.fillerBlock)
		);
	}
}
