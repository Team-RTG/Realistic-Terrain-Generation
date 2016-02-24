package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBog;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBog;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPBog extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.bog.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPBog(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBog(),
			new SurfaceBOPBog(config, topBlock, fillerBlock)
		);
	}
}
