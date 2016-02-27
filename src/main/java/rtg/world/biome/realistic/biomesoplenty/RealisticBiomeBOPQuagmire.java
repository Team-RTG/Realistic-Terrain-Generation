package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPQuagmire;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPQuagmire;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPQuagmire extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.quagmire.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPQuagmire(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPQuagmire(),
			new SurfaceBOPQuagmire(config, topBlock, fillerBlock)
		);
	}
}
