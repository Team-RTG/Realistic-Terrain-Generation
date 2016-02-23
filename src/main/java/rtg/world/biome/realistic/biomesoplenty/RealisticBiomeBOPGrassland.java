package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGrassland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGrassland;

public class RealisticBiomeBOPGrassland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.grassland.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPGrassland(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPGrassland(),
			new SurfaceBOPGrassland(config, topBlock, fillerBlock)
		);
	}
}
