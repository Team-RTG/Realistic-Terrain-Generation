package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBCarr;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoEBTree;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.vanilla.WorldGenForestRTG;
import rtg.world.gen.feature.tree.vanilla.WorldGenTreesRTG;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBCarr;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBCarr;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBCarr extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.SCHIST, (byte)0),
        EBAPI.ebStonify(EBAPI.SHALE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.SCHIST, (byte)0),
        EBAPI.ebStonify(EBAPI.SHALE, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.HISTOSOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.HISTOSOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.HISTOSOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.HISTOSOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.SCHIST, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone);
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.SCHIST, (byte)0);
    
    public RealisticBiomeEBCarr(BiomeGenBase ebBiome, BiomeConfig config)
    {
    
        super(config, 
            ebBiome, BiomeGenBase.river,
            new TerrainEBCarr(),
            new SurfaceEBCarr(config,
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
        
        DecoEBTree ebTrees = new DecoEBTree();
		ebTrees.treeType = rtg.world.biome.deco.DecoEBTree.TreeType.ALDER;
		ebTrees.strengthNoiseFactorForLoops = true;
		ebTrees.distribution.noiseDivisor = 80f;
		ebTrees.distribution.noiseFactor = 60f;
		ebTrees.distribution.noiseAddend = -15f;
		ebTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		ebTrees.treeConditionChance = 2;
		ebTrees.maxY = 110;
		this.addDeco(ebTrees);

		DecoTree oakPine = new DecoTree(new TreeRTGPiceaSitchensis());
    	oakPine.logBlock = Blocks.log;
    	oakPine.logMeta = (byte)0;
    	oakPine.leavesBlock = Blocks.leaves;
    	oakPine.leavesMeta = (byte)0;
    	oakPine.minTrunkSize = 4;
    	oakPine.maxTrunkSize = 10;
    	oakPine.minCrownSize = 6;
    	oakPine.maxCrownSize = 14;
		oakPine.strengthFactorForLoops = 3f;
    	oakPine.treeType = TreeType.RTG_TREE;
		oakPine.distribution.noiseDivisor = 80f;
		oakPine.distribution.noiseFactor = 60f;
		oakPine.distribution.noiseAddend = -15f;
		oakPine.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		oakPine.treeConditionNoise = 5f;
		oakPine.treeConditionChance = 1;
		oakPine.maxY = 120;
        
        DecoTree vanillaTrees = new DecoTree(new WorldGenTreesRTG());
		vanillaTrees.strengthFactorForLoops = 3f;
        vanillaTrees.treeType = TreeType.WORLDGEN;
		vanillaTrees.distribution.noiseDivisor = 80f;
		vanillaTrees.distribution.noiseFactor = 60f;
		vanillaTrees.distribution.noiseAddend = -15f;
		vanillaTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		vanillaTrees.treeConditionNoise = 5f;
		vanillaTrees.treeConditionChance = 1;
		vanillaTrees.maxY = 120;
        
        DecoTree vanillaForest = new DecoTree(new WorldGenForestRTG());
		vanillaForest.strengthFactorForLoops = 3f;
        vanillaForest.treeType = TreeType.WORLDGEN;
		vanillaForest.distribution.noiseDivisor = 80f;
		vanillaForest.distribution.noiseFactor = 60f;
		vanillaForest.distribution.noiseAddend = -15f;
		vanillaForest.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		vanillaForest.treeConditionNoise = 5f;
		vanillaForest.treeConditionChance = 1;
		vanillaForest.maxY = 120;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{oakPine, vanillaTrees, vanillaForest};
		decoHelperRandomSplit.chances = new int[]{8, 4, 1};
		this.addDeco(decoHelperRandomSplit);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree.logConditionNoise = 8f;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.maxY = 100;
        decoFallenTree.randomLogBlocks = new Block[]{EnhancedBiomesBlocks.logBirch, Blocks.log};
        decoFallenTree.randomLogMetas = new byte[]{0, 1};
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigEBCarr.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.chance = 1;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
