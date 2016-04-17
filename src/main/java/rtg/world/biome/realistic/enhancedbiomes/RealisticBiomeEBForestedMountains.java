package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBForestedMountains;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoEBTree;
import rtg.world.biome.deco.DecoEBTree.TreeType;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBForestedMountains;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBForestedMountains;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBForestedMountains extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.CHERT, (byte)0),
        EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.CHERT, (byte)0),
        EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.CHERT, (byte)0);
    private static Block ebCliff2Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.CHERT, (byte)0);
    
	public RealisticBiomeEBForestedMountains(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBForestedMountains(440f, 35f),
			new SurfaceEBForestedMountains(config,
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
        
        DecoEBTree ebTreesGreatOak = new DecoEBTree();
		ebTreesGreatOak.strengthFactorForLoops = 2f;
		ebTreesGreatOak.treeType = TreeType.GREAT_OAK;
		ebTreesGreatOak.distribution.noiseDivisor = 80f;
		ebTreesGreatOak.distribution.noiseFactor = 60f;
		ebTreesGreatOak.distribution.noiseAddend = -15f;
		ebTreesGreatOak.treeCondition = TreeCondition.RANDOM_CHANCE;
		ebTreesGreatOak.treeConditionChance = 2;
		ebTreesGreatOak.maxY = 230;
		
        DecoEBTree ebTreesBirch = new DecoEBTree();
		ebTreesBirch.strengthFactorForLoops = 2f;
		ebTreesBirch.treeType = TreeType.BIRCH;
		ebTreesBirch.distribution.noiseDivisor = 80f;
		ebTreesBirch.distribution.noiseFactor = 60f;
		ebTreesBirch.distribution.noiseAddend = -15f;
		ebTreesBirch.treeCondition = TreeCondition.RANDOM_CHANCE;
		ebTreesBirch.treeConditionChance = 2;
		ebTreesBirch.maxY = 230;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{ebTreesGreatOak, ebTreesBirch};
		decoHelperRandomSplit.chances = new int[]{32, 1};
		this.addDeco(decoHelperRandomSplit);        
        
        DecoEBTree ebTrees = new DecoEBTree();
		ebTrees.treeType = TreeType.FORESTED_VALLEY;
		ebTrees.strengthFactorForLoops = 4f;
		ebTrees.distribution.noiseDivisor = 80f;
		ebTrees.distribution.noiseFactor = 60f;
		ebTrees.distribution.noiseAddend = -15f;
		ebTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		ebTrees.treeConditionNoise = 5f;
		ebTrees.treeConditionChance = 4;
		ebTrees.maxY = 220;
		this.addDeco(ebTrees);        
        
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree.logConditionNoise = 32f;
        decoFallenTree.maxY = 100;
        decoFallenTree.randomLogBlocks = new Block[]{Blocks.log, Blocks.log};
        decoFallenTree.randomLogMetas = new byte[]{0, 2};
        decoFallenTree.leavesBlock = Blocks.leaves;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigEBForestedMountains.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.chance = 1;
        decoShrub.strengthFactor = 23f;
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
