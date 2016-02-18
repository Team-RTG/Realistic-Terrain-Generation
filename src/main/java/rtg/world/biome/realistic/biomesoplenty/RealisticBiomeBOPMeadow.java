package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMeadow;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMeadow;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMeadow extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.meadow;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPMeadow(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMeadow(),
			new SurfaceBOPMeadow(topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
