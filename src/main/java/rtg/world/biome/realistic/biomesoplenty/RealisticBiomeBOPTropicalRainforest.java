package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropicalRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTropicalRainforest;

public class RealisticBiomeBOPTropicalRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.tropical_rainforest.get();
	
	public static IBlockState topBlock = bopBiome.topBlock;
	public static IBlockState fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPTropicalRainforest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPTropicalRainforest(0f, 60f, 68f, 200f),
			new SurfaceBOPTropicalRainforest(config, topBlock, fillerBlock)
		);
	}
}
