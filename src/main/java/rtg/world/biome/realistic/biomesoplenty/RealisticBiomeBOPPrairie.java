package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPPrairie;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPPrairie;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPPrairie extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.prairie;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPPrairie(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPPrairie(65f, 80f, 25f),
			new SurfaceBOPPrairie(topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
