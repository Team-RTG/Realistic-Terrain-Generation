package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOriginValley;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOriginValley;

public class RealisticBiomeBOPOriginValley extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.origin_island.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPOriginValley(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPOriginValley(65f, 80f, 38f),
			new SurfaceBOPOriginValley(config, topBlock, fillerBlock)
		);
	}
}
