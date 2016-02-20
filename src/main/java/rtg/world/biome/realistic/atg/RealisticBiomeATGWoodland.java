package rtg.world.biome.realistic.atg;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.atg.SurfaceATGWoodland;
import rtg.world.gen.terrain.atg.TerrainATGWoodland;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeATGWoodland extends RealisticBiomeATGBase
{	
	public RealisticBiomeATGWoodland(BiomeGenBase atgBiome, BiomeConfig config)
	{
		super(config, 
			atgBiome, BiomeGenBase.river,
			new TerrainATGWoodland(),
			new SurfaceATGWoodland(config, atgBiome.topBlock.getBlock(), atgBiome.fillerBlock.getBlock())
		);
	}
}
