package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMeadow;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMeadow;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMeadow extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.meadow;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMeadow(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMeadow(),
			new SurfaceBOPMeadow(config, topBlock, fillerBlock)
		);
	}
}
