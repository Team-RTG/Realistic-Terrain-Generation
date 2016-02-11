package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTemperateRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTemperateRainforest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPTemperateRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.temperateRainforest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPTemperateRainforest(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPTemperateRainforest(),
			new SurfaceBOPTemperateRainforest(config, topBlock, fillerBlock, false, null, 0.45f)
		);
		
		this.config = config;
	}
}
