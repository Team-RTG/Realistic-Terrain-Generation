package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBClearing;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBClearing;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBClearing extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(Blocks.stone, Blocks.stone),
        EBAPI.ebStonify(Blocks.stone, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify((byte)0, (byte)0),
        EBAPI.ebStonify((byte)0, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(Blocks.cobblestone, Blocks.cobblestone),
        EBAPI.ebStonify(Blocks.cobblestone, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify((byte)0, (byte)0),
        EBAPI.ebStonify((byte)0, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.MOLLISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.MOLLISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.MOLLISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.MOLLISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(Blocks.stone, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify((byte)0, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(Blocks.cobblestone, Blocks.cobblestone);
    private static byte ebCliff2Byte = EBAPI.ebStonify((byte)0, (byte)0);
    
	public RealisticBiomeEBClearing(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBClearing(),
			new SurfaceEBClearing(config, 
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
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.config = config;
        
    }
}
