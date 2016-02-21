package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.tofucraft.SurfaceTOFUTofuExtremeHills;
import rtg.world.gen.terrain.tofucraft.TerrainTOFUTofuExtremeHills;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFUTofuExtremeHills extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFUTofuExtremeHills(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFUTofuExtremeHills(10f, 120f, 68f, 200f),
			new SurfaceTOFUTofuExtremeHills(config, tofuBiome.topBlock, tofuBiome.fillerBlock, tofuBiome.topBlock, tofuBiome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
		);
	}
}
