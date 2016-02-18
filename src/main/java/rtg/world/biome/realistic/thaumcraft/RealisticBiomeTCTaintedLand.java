package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.gen.surface.thaumcraft.SurfaceTCTaintedLand;
import rtg.world.gen.terrain.thaumcraft.TerrainTCTaintedLand;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCTaintedLand extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCTaintedLand(BiomeGenBase tcBiome, BiomeConfig config)
	{
		super(
			tcBiome, BiomeGenBase.river,
			new TerrainTCTaintedLand(),
			new SurfaceTCTaintedLand(tcBiome.topBlock.getBlock(), tcBiome.fillerBlock.getBlock())
		);
		
		this.config = config;
	}
}
