package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeadSwamp;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeadSwamp;

public class RealisticBiomeBOPDeadSwamp extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.dead_swamp.get();
	
    public static Block topBlock = BOPBlocks.grass;
    public static Block fillerBlock = BOPBlocks.dirt;
	
	public RealisticBiomeBOPDeadSwamp(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPDeadSwamp(),
			new SurfaceBOPDeadSwamp(config, topBlock, fillerBlock)
		);
	}
}
