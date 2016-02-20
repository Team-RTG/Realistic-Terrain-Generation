package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPRainforest;

public class RealisticBiomeBOPRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.rainforest;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPRainforest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPRainforest(90f, 300f),
			new SurfaceBOPRainforest(config, topBlock, fillerBlock, false, null, 1.3f)
		);
	}
}
