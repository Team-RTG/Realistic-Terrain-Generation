package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biomes.thaumcraft.config.BiomeConfigTCMagicalForest;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.thaumcraft.SurfaceTCMagicalForest;
import rtg.world.gen.terrain.thaumcraft.TerrainTCMagicalForest;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCMagicalForest extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCMagicalForest(BiomeGenBase tcBiome)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainTCMagicalForest(),
			new SurfaceTCMagicalForest(tcBiome.topBlock, tcBiome.fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigTCMagicalForest();
		this.biomeWeight = ConfigTC.weightTCMagicalForest;
		this.generateVillages = ConfigTC.villageTCMagicalForest;
	}
}
