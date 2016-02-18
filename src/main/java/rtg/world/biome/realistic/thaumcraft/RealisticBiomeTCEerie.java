
package rtg.world.biome.realistic.thaumcraft;

import rtg.api.biome.BiomeConfig;
import rtg.config.thaumcraft.ConfigTC;
import rtg.world.gen.surface.thaumcraft.SurfaceTCEerie;
import rtg.world.gen.terrain.thaumcraft.TerrainTCEerie;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeTCEerie extends RealisticBiomeTCBase
{
	public RealisticBiomeTCEerie(BiomeGenBase tcBiome, BiomeConfig config)
	{
		super(
			tcBiome, BiomeGenBase.river,
			new TerrainTCEerie(),
			new SurfaceTCEerie(tcBiome.topBlock.getBlock(), tcBiome.fillerBlock.getBlock())
		);

        this.config = config;
	}
}