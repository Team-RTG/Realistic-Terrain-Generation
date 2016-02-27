package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOriginIsland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOriginIsland;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPOriginIsland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.origin_island.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPOriginIsland(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPOriginIsland(65f, 80f, 38f),
			new SurfaceBOPOriginIsland(config, topBlock, fillerBlock)
		);
	}
}
