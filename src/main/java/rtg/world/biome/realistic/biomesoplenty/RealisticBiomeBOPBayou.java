package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBayou;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBayou;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPBayou extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.bayou.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPBayou(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBayou(),
			new SurfaceBOPBayou(config, topBlock, fillerBlock)
		);
	}
}
