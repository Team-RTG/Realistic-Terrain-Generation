package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPScrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPScrubland;

public class RealisticBiomeBOPScrubland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.alps.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPScrubland(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPScrubland(58f, 80f, 30f),
			new SurfaceBOPScrubland(config, topBlock, fillerBlock)
		);
	}
}
