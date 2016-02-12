package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPArctic;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPArctic;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPArctic extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.arctic;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPArctic(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.frozenRiver,
			new TerrainBOPArctic(),
			new SurfaceBOPArctic(config, 
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
