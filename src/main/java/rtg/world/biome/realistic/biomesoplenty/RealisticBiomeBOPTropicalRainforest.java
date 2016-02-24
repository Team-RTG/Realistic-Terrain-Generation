package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropicalRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTropicalRainforest;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPTropicalRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.tropical_rainforest.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPTropicalRainforest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPTropicalRainforest(0f, 140f, 68f, 200f),
			new SurfaceBOPTropicalRainforest(config, topBlock, fillerBlock)
		);
	}
}
