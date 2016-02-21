package rtg.world.biome.realistic.tofucraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGWoodland;
import rtg.world.gen.terrain.atg.TerrainATGWoodland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTOFULeekPlains extends RealisticBiomeTOFUBase
{	
	public RealisticBiomeTOFULeekPlains(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGWoodland(),
			new SurfaceATGWoodland(config, atgBiome.topBlock, atgBiome.fillerBlock)
		);
	}
}
