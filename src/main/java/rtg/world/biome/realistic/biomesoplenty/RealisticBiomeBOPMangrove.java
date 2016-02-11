package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMangrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMangrove;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMangrove extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.mangrove;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMangrove(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMangrove(),
			new SurfaceBOPMangrove(config, topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
