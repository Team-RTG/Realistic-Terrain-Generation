package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.gen.surface.thaumcraft.SurfaceTCMagicalForest;
import rtg.world.gen.terrain.thaumcraft.TerrainTCMagicalForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCMagicalForest extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCMagicalForest(BiomeGenBase tcBiome, BiomeConfig config)
	{
		super(
			tcBiome, BiomeGenBase.river,
			new TerrainTCMagicalForest(),
			new SurfaceTCMagicalForest(tcBiome.topBlock, tcBiome.fillerBlock)
		);
		
		this.config = config;
	}
}
