package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPRainforest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.rainforest;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPRainforest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPRainforest(90f, 300f),
			new SurfaceBOPRainforest(topBlock, fillerBlock, false, null, 1.3f)
		);
		
		this.config = config;
	}
}
