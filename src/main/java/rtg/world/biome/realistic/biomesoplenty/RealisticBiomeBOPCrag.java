package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCrag;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCrag;

public class RealisticBiomeBOPCrag extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.crag.get();
	
	public static IBlockState topBlock = bopBiome.topBlock;
	public static IBlockState fillerBlock = bopBiome.fillerBlock;
		
	public RealisticBiomeBOPCrag(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPCrag(90f),
			new SurfaceBOPCrag(config, topBlock, fillerBlock, topBlock)
		);
		this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;
	}
}
