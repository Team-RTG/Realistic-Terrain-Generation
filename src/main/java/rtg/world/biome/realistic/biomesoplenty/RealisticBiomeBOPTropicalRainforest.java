package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropicalRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTropicalRainforest;

public class RealisticBiomeBOPTropicalRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.tropicalRainforest;
	
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
