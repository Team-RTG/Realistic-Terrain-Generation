package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushRiver;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushRiver;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPLushRiver extends RealisticBiomeBOPBase
{
	public static BiomeGenBase bopBiome = BOPCBiomes.lushRiver;

	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;

	public RealisticBiomeBOPLushRiver(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPLushRiver(),
			new SurfaceBOPLushRiver(config)
		);

		this.config = config;
	}
}
