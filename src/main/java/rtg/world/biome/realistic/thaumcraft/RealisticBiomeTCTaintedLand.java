package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biomes.thaumcraft.config.BiomeConfigTCTaintedLand;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.thaumcraft.SurfaceTCTaintedLand;
import rtg.world.gen.terrain.thaumcraft.TerrainTCTaintedLand;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCTaintedLand extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCTaintedLand(BiomeGenBase tcBiome)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainTCTaintedLand(),
			new SurfaceTCTaintedLand(tcBiome.topBlock, tcBiome.fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigTCTaintedLand();
		this.biomeWeight = ConfigTC.weightTCTaintedLand;
		this.generateVillages = ConfigTC.villageTCTaintedLand;
	}
}
