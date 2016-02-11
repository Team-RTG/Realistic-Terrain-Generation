package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSacredSprings;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSacredSprings;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPSacredSprings extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.sacredSprings;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSacredSprings(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSacredSprings(),
			new SurfaceBOPSacredSprings(config, topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
