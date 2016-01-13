package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.thaumcraft.SurfaceTCTaintedLand;
import rtg.world.gen.terrain.thaumcraft.TerrainTCTaintedLand;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCTaintedLand extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCTaintedLand(BiomeGenBase tcBiome, BiomeConfig config)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainTCTaintedLand(),
			new SurfaceTCTaintedLand(tcBiome.topBlock, tcBiome.fillerBlock)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigTC.weightTCTaintedLand;
		this.generateVillages = ConfigTC.villageTCTaintedLand;
	}
}
