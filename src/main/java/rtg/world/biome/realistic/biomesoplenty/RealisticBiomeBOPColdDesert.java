package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPColdDesert;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPColdDesert;
import biomesoplenty.api.biome.BOPBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPColdDesert extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.cold_desert.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPColdDesert(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.frozenRiver,
			new TerrainBOPColdDesert(),
			new SurfaceBOPColdDesert(config,
			    Blocks.snow, //Block top 
			    (byte)0, //byte topByte
			    fillerBlock, //Block filler, 
                (byte)0, //byte fillerByte
                Blocks.snow, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                fillerBlock, //Block mixFill, 
                (byte)0, //byte mixFillByte,
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
	}
}
