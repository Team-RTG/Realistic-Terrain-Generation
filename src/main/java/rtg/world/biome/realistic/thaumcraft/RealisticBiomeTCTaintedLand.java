package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.thaumcraft.SurfaceTCTaintedLand;
import rtg.world.gen.terrain.thaumcraft.TerrainTCTaintedLand;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCTaintedLand extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCTaintedLand(BiomeGenBase tcBiome, BiomeConfig config)
	{
		super(config, 
			tcBiome, BiomeGenBase.river,
			new TerrainTCTaintedLand(),
			new SurfaceTCTaintedLand(config, tcBiome.topBlock, tcBiome.fillerBlock)
		);
	}
}
