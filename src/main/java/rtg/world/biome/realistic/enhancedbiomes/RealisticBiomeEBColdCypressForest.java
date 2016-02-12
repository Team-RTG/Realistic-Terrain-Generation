package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBColdCypressForest;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBColdCypressForest;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBColdCypressForest extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0),
        EBAPI.ebStonify(EBAPI.SLATE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0),
        EBAPI.ebStonify(EBAPI.SLATE, (byte)0)
    };
    
    private static Block ebTopBlock = Blocks.grass;
    private static byte ebTopByte = (byte)0;
    private static Block ebFillBlock = Blocks.dirt;
    private static byte ebFillByte = (byte)0;
    private static Block ebMixTopBlock = Blocks.grass;
    private static byte ebMixTopByte = (byte)0;
    private static Block ebMixFillBlock = Blocks.dirt;
    private static byte ebMixFillByte = (byte)0;
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0);
    private static Block ebCliff2Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0);
    
	public RealisticBiomeEBColdCypressForest(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBColdCypressForest(160f, 80f, 60f),
			new SurfaceEBColdCypressForest(config, 
                ebTopBlock, //Block top 
                ebTopByte, //byte topByte
                ebFillBlock, //Block filler, 
                ebFillByte, //byte fillerByte
                ebMixTopBlock, //Block mixTop, 
                ebMixTopByte, //byte mixTopByte, 
                ebMixFillBlock, //Block mixFill, 
                ebMixFillByte, //byte mixFillByte, 
                ebCliff1Block, //Block cliff1, 
                ebCliff1Byte, //byte cliff1Byte, 
                ebCliff2Block, //Block cliff2, 
                ebCliff2Byte, //byte cliff2Byte, 
                1f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                2f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
        
    }
}
