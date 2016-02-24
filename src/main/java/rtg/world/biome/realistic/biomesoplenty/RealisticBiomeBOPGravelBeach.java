package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGravelBeach;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGravelBeach;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPGravelBeach extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.alps.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPGravelBeach(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPGravelBeach(),
			new SurfaceBOPGravelBeach(
				config,
				bopBiome.topBlock.getBlock(),
				bopBiome.fillerBlock.getBlock(),
				bopBiome.topBlock.getBlock(),
				bopBiome.fillerBlock.getBlock(),
				(byte)0,
				1
			)
		);
	}
}
