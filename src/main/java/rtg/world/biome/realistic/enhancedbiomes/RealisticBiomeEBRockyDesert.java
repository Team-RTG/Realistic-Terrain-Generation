package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenerator;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoEBTree.TreeType;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRockyDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRockyDesert;

public class RealisticBiomeEBRockyDesert extends RealisticBiomeEBBase
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
    private static Block ebMixTopBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.sandstone);
    private static byte ebMixTopByte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    private static Block ebMixFillBlock = Blocks.sandstone;
    private static byte ebMixFillByte = (byte)0;
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    private static Block ebCliff2Block = ebDominantCobblestoneBlock[0];
    private static byte ebCliff2Byte = ebDominantCobblestoneMeta[0];
    private WorldGenerator blockBlob = new WorldGenBlockBlob(Blocks.cobblestone, 0);
    
	public RealisticBiomeEBRockyDesert(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBRockyDesert(120f, 30f, 68f),
			new SurfaceEBRockyDesert(config,
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
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.maxY = 80;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);

        DecoEBTree ebShrub = new DecoEBTree();
        ebShrub.checkRiver = true;
        ebShrub.minRiver = 0.86f;
		ebShrub.treeType = TreeType.EUCALYPTUS_SHRUB;
		ebShrub.strengthFactorForLoops = 10f;
		ebShrub.distribution.noiseDivisor = 80f;
		ebShrub.distribution.noiseFactor = 60f;
		ebShrub.distribution.noiseAddend = -15f;
		ebShrub.treeCondition = TreeCondition.ALWAYS_GENERATE;
		ebShrub.maxY = 100;
		
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.checkRiver = true;
        decoShrub.minRiver = 0.86f;            
        decoShrub.maxY = 100;
        decoShrub.chance = 1;
        decoShrub.strengthFactor = 10f;
        
        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{decoShrub, ebShrub};
        decoHelperRandomSplit.chances = new int[]{4, 1};
        this.addDeco(decoHelperRandomSplit);
            
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

        DecoEBRockSpire decoEBRockSpire = new DecoEBRockSpire();
        decoEBRockSpire.materials = new Block[] {
        	EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone), 
        	(EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone, 
        	Blocks.sandstone 
        };
        decoEBRockSpire.meta = new byte[] {
        	EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0), 
        	EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0), 
        	0
        };
        decoEBRockSpire.height = 10;
        decoEBRockSpire.chance = 8;
        this.addDeco(decoEBRockSpire);
        
		DecoCactus decoCactus = new DecoCactus();
		decoCactus.maxY = 120;
		decoCactus.strengthFactor = 5f;
        this.addDeco(decoCactus);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 128;
		decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);
    }

    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {
        this.rReplaceRiverSurface(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
