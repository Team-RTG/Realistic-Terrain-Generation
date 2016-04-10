package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoDesertWell;
import rtg.world.biome.deco.DecoEBRockSpire;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSahara;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSahara;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBSahara extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0),
        EBAPI.ebStonify(EBAPI.MARBLE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0),
        EBAPI.ebStonify(EBAPI.MARBLE, (byte)0)
    };
    
    private static Block ebTopBlock = Blocks.sand;
    private static byte ebTopByte = (byte)0;
    private static Block ebFillBlock = Blocks.sandstone;
    private static byte ebFillByte = (byte)0;
    private static Block ebMixTopBlock = Blocks.sand;
    private static byte ebMixTopByte = (byte)0;
    private static Block ebMixFillBlock = Blocks.sandstone;
    private static byte ebMixFillByte = (byte)0;
    private static Block ebCliff1Block = Blocks.sandstone;
    private static byte ebCliff1Byte = (byte)0;
    private static Block ebCliff2Block = Blocks.sandstone;
    private static byte ebCliff2Byte = (byte)0;
    
	public RealisticBiomeEBSahara(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBSahara(),
			new SurfaceEBSahara(config, 
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
        
        DecoEBRockSpire decoEBRockSpire = new DecoEBRockSpire();
        decoEBRockSpire.materials = new Block[]{ebDominantStoneBlock[0], ebDominantCobblestoneBlock[0], Blocks.sandstone};
        decoEBRockSpire.meta = new byte[]{ebDominantStoneMeta[0], ebDominantCobblestoneMeta[0], (byte)0};
        decoEBRockSpire.height = 10;
        decoEBRockSpire.chance = 3;
        this.addDeco(decoEBRockSpire);
        
        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.chance = 500;
        this.addDeco(decoDesertWell);
    }
}
