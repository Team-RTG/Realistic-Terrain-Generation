package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLandOfLakesMarsh;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLandOfLakesMarsh;

public class RealisticBiomeBOPLandOfLakesMarsh extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.landOfLakesMarsh;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPLandOfLakesMarsh(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPLandOfLakesMarsh(),
			new SurfaceBOPLandOfLakesMarsh(config, topBlock, fillerBlock)
		);
	}
}
