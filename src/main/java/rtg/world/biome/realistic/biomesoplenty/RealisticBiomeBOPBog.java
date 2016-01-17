package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBog;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBog;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPBog extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.bog;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBog(BiomeConfig config)
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainBOPBog(),
			new SurfaceBOPBog(topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
