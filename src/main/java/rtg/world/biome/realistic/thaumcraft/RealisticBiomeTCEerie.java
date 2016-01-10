
package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biomes.BiomeConfig;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.thaumcraft.SurfaceTCEerie;
import rtg.world.gen.terrain.thaumcraft.TerrainTCEerie;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCEerie extends RealisticBiomeTCBase
{
	public RealisticBiomeTCEerie(BiomeGenBase tcBiome, BiomeConfig config)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainTCEerie(),
			new SurfaceTCEerie(tcBiome.topBlock, tcBiome.fillerBlock)
		);

        this.biomeConfig = config;
        this.biomeWeight = ConfigTC.weightTCEerie;
        this.generateVillages = ConfigTC.villageTCEerie;
	}
}