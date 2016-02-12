package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPChaparral;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPChaparral;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPChaparral extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.chaparral;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPChaparral(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPChaparral(),
			new SurfaceBOPChaparral(config, topBlock, fillerBlock, Blocks.sand, 26f, 0.35f)
		);
	}
}
