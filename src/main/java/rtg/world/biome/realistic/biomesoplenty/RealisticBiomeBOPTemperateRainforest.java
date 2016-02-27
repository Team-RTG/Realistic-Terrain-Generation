package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTemperateRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTemperateRainforest;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPTemperateRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.temperate_rainforest.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPTemperateRainforest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPTemperateRainforest(),
			new SurfaceBOPTemperateRainforest(config, topBlock, fillerBlock, false, null, 0.45f)
		);
	}
}
