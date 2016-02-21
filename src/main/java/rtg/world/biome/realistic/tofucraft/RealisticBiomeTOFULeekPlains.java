package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.tofucraft.SurfaceTOFULeekPlains;
import rtg.world.gen.terrain.tofucraft.TerrainTOFULeekPlains;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFULeekPlains extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFULeekPlains(BiomeGenBase tofuBiome, BiomeConfig config)
	{
		super(config, 
			tofuBiome, BiomeGenBase.river,
			new TerrainTOFULeekPlains(),
			new SurfaceTOFULeekPlains(config, tofuBiome.topBlock, tofuBiome.fillerBlock)
		);
	}
}
