package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLandOfLakesMarsh;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLandOfLakesMarsh;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPLandOfLakesMarsh extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.landOfLakesMarsh;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPLandOfLakesMarsh(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPLandOfLakesMarsh(),
			new SurfaceBOPLandOfLakesMarsh(topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
