package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCoralReef;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCoralReef;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPCoralReef extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.coral_reef.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPCoralReef(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPCoralReef(false, -10f, 0f, 0f, 0f, 30f),
			new SurfaceBOPCoralReef(config, topBlock, fillerBlock)
		);
	}
}
