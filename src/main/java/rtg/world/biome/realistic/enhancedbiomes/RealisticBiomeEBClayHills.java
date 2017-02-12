package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBClayHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBClayHills;

public class RealisticBiomeEBClayHills extends RealisticBiomeEBBase
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
    
    private static Block ebTopBlock = Blocks.sandstone;
    private static byte ebTopByte = (byte)0;
    private static Block ebFillBlock = Blocks.sandstone;
    private static byte ebFillByte = (byte)0;
    private static Block ebMixTopBlock = Blocks.hardened_clay;
    private static byte ebMixTopByte = (byte)0;
    private static Block ebMixFillBlock = Blocks.hardened_clay;
    private static byte ebMixFillByte = (byte)0;
    private static Block ebCliff1Block = Blocks.hardened_clay;
    private static byte ebCliff1Byte = (byte)0;
    private static Block ebCliff2Block = Blocks.hardened_clay;
    private static byte ebCliff2Byte = (byte)0;
    
    public RealisticBiomeEBClayHills(BiomeGenBase ebBiome, BiomeConfig config)
    {
    
        super(config, 
            ebBiome, BiomeGenBase.river,
            new TerrainEBClayHills(true, 35f, 160f, 60f, 40f, 69f),
            new SurfaceEBClayHills(config,
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
        
        this.addDecoCollection(new DecoCollectionDesertRiver());
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.maxY = 83;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);

        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
		decoReed.maxY = 68;
		decoReed.strengthFactor = 2f;
        this.addDeco(decoReed); 
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.checkRiver = true;
		decoFlowersRTG.minRiver = 0.7f;
		decoFlowersRTG.flowers = new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.loops = 3;
        this.addDeco(decoFlowersRTG);
        
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.checkRiver = true;
        decoGrassDoubleTallgrass.minRiver = 0.7f;
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);          

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 90;
        decoShrub.chance = 5;
        decoShrub.loops = 3;
		this.addDeco(decoShrub);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 120;
		decoDeadBush.loops = 3;
        this.addDeco(decoDeadBush);
        
		DecoCactus decoCactus = new DecoCactus();
		decoCactus.maxY = 120;
		decoCactus.loops = 18;
        this.addDeco(decoCactus);
    }

    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        this.rReplaceRiverSurface(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
