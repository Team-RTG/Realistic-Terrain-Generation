package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPPrairie;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPPrairie;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPPrairie extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.prairie.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPPrairie(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPPrairie(65f, 80f, 25f),
			new SurfaceBOPPrairie(config, topBlock, fillerBlock)
		);
	}
}
