package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPShrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPShrubland;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPShrubland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.shrubland.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPShrubland(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPShrubland(),
			new SurfaceBOPShrubland(config, topBlock, fillerBlock)
		);
	}
}
