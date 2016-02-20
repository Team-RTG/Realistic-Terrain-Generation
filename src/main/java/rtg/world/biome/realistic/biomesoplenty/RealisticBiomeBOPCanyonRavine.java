package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCanyonRavine;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCanyonRavine;

public class RealisticBiomeBOPCanyonRavine extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.canyonRavine;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPCanyonRavine(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPCanyonRavine(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceBOPCanyonRavine(config, topBlock, (byte)0, fillerBlock, (byte)0, 0)
		);
	}
}
