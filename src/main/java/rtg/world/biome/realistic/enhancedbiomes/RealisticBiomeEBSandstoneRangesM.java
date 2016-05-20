package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSandstoneRangesM;
import rtg.world.biome.deco.DecoEBTree;
import rtg.world.biome.deco.DecoEBTree.TreeType;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneRangesM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneRangesM;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBSandstoneRangesM extends RealisticBiomeEBBase
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
    private static Block ebFillBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.sandstone);
    private static byte ebFillByte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.OXISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.sandstone);
    private static byte ebMixFillByte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.sandstone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.sandstone);
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    
	public RealisticBiomeEBSandstoneRangesM(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBSandstoneRangesM(false, 35f, 160f, 40f, 30f, 60),
			new SurfaceEBSandstoneRangesM(config,
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

        noWaterFeatures= true;
        
        DecoEBTree ebCypressTrees = new DecoEBTree();
		ebCypressTrees.treeType = TreeType.CYPRESS;
		ebCypressTrees.strengthFactorForLoops = 3f;
		ebCypressTrees.distribution.noiseDivisor = 80f;
		ebCypressTrees.distribution.noiseFactor = 60f;
		ebCypressTrees.distribution.noiseAddend = -15f;
		ebCypressTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		ebCypressTrees.treeConditionNoise = 5f;
		ebCypressTrees.treeConditionChance = 2;
		ebCypressTrees.maxY = 120;
		
        DecoEBTree ebEucalyptusTrees = new DecoEBTree();
		ebEucalyptusTrees.treeType = TreeType.EUCALYPTUS;
		ebEucalyptusTrees.strengthFactorForLoops = 3f;
		ebEucalyptusTrees.distribution.noiseDivisor = 80f;
		ebEucalyptusTrees.distribution.noiseFactor = 60f;
		ebEucalyptusTrees.distribution.noiseAddend = -15f;
		ebEucalyptusTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		ebEucalyptusTrees.treeConditionNoise = 5f;
		ebEucalyptusTrees.treeConditionChance = 2;
		ebEucalyptusTrees.maxY = 120;		
		
        DecoHelper5050 decoHelper5050_3 = new DecoHelper5050(ebCypressTrees, ebEucalyptusTrees);
		this.addDeco(decoHelper5050_3);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree1.logConditionNoise = 8f;
        decoFallenTree1.logConditionChance = 8;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = EnhancedBiomesBlocks.logSpruce;
        decoFallenTree1.logMeta = (byte)1;
        decoFallenTree1.leavesBlock = EnhancedBiomesBlocks.leavesSpruce;
        decoFallenTree1.leavesMeta = (byte)-1;
        decoFallenTree1.minSize = 4;
        decoFallenTree1.maxSize = 6;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree2.logConditionNoise = 8f;
        decoFallenTree2.logConditionChance = 8;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = EnhancedBiomesBlocks.logBirch;
        decoFallenTree2.logMeta = (byte)1;
        decoFallenTree2.leavesBlock = EnhancedBiomesBlocks.leavesBirch;
        decoFallenTree2.leavesMeta = (byte)-1;
        decoFallenTree2.minSize = 4;
        decoFallenTree2.maxSize = 6;
        
        DecoHelper5050 decoHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
		this.addDeco(decoHelper5050, this.config._boolean(BiomeConfigEBSandstoneRangesM.decorationLogsId));
        
        DecoEBTree ebShrub = new DecoEBTree();
		ebShrub.treeType = TreeType.EUCALYPTUS_SHRUB;
		ebShrub.strengthFactorForLoops = 2f;
		ebShrub.distribution.noiseDivisor = 80f;
		ebShrub.distribution.noiseFactor = 60f;
		ebShrub.distribution.noiseAddend = -15f;
		ebShrub.treeCondition = TreeCondition.RANDOM_CHANCE;
		ebShrub.treeConditionChance = 2;
		ebShrub.maxY = 110;       
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.chance = 2;
        decoShrub.strengthFactor = 2f;
        
        DecoHelper5050 decoHelper5050_2 = new DecoHelper5050(ebShrub, decoShrub);
		this.addDeco(decoHelper5050_2);

		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.chance = 1;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
