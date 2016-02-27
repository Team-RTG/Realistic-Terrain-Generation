package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCrag;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCrag;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPCrag extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.crag.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
		
	public RealisticBiomeBOPCrag(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPCrag(false, new float[]{2.0f, 0.5f, 6.0f, 0.5f, 10.0f, 0.5f, 14.0f, 0.5f, 18.0f, 0.5f, 22.0f, 0.5f, 26.0f, 0.5f, 30.0f, 0.5f, 34.0f, 0.5f}, 40f, 1f, 1f, 0.5f, 69f),
			new SurfaceBOPCrag(config, topBlock, fillerBlock, topBlock)
		);
		this.generatesEmeralds = true;
	}
}
