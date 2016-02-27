package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMarsh;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMarsh;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMarsh extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.marsh.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPMarsh(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMarsh(),
			new SurfaceBOPMarsh(config, topBlock, fillerBlock)
		);
	}
}
