package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTundra;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTundra;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPTundra extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.tundra.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPTundra(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPTundra(),
			new SurfaceBOPTundra(config, topBlock, fillerBlock)
		);
	}
}
