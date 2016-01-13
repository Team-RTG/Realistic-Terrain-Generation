package rtg.world.biome.realistic.enhancedbiomes;

import rtg.api.biome.BiomeConfig;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRedDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRedDesert;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBRedDesert extends RealisticBiomeEBBase
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
    private static byte ebTopByte = (byte)1;
    private static Block ebFillBlock = ebDominantStoneBlock[0];
    private static byte ebFillByte = ebDominantStoneMeta[0];
    private static Block ebMixTopBlock = Blocks.sand;
    private static byte ebMixTopByte = (byte)1;
    private static Block ebMixFillBlock = Blocks.sand;
    private static byte ebMixFillByte = (byte)1;
    private static Block ebCliff1Block = Blocks.sand;
    private static byte ebCliff1Byte = (byte)1;
    private static Block ebCliff2Block = Blocks.sand;
    private static byte ebCliff2Byte = (byte)1;
    
    public RealisticBiomeEBRedDesert(BiomeGenBase ebBiome, BiomeConfig config)
    {
    
        super(
            ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
            new TerrainEBRedDesert(),
            new SurfaceEBRedDesert(
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
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigEB.weightEBRedDesert;
        this.generateVillages = ConfigEB.villageEBRedDesert;
        
    }
}
