package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCanyonRavine;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCanyonRavine;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPCanyonRavine extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.canyonRavine;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPCanyonRavine(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPCanyonRavine(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceBOPCanyonRavine(topBlock, fillerBlock, (byte)0, 0)
		);
		
		this.config = config;
	}
}
