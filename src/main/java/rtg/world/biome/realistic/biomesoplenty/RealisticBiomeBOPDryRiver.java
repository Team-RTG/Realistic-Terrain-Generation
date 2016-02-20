package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDryRiver;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDryRiver;

public class RealisticBiomeBOPDryRiver extends RealisticBiomeBOPBase
{
	public static BiomeGenBase bopBiome = BOPCBiomes.dryRiver;

	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();

	public RealisticBiomeBOPDryRiver(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPDryRiver(),
			new SurfaceBOPDryRiver(config)
		);
	}
}
