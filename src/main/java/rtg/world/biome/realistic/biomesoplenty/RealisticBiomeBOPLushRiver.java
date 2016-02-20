package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushRiver;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushRiver;

public class RealisticBiomeBOPLushRiver extends RealisticBiomeBOPBase
{
	public static BiomeGenBase bopBiome = BOPCBiomes.lushRiver;

	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();

	public RealisticBiomeBOPLushRiver(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPLushRiver(),
			new SurfaceBOPLushRiver(config)
		);
	}
}
