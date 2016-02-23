package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPArctic;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPArctic;

public class RealisticBiomeBOPArctic extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.cold_desert.get();
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
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
