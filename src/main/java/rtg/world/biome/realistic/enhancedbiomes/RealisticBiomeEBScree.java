package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBScree;
import rtg.world.biome.deco.DecoEBTree;
import rtg.world.biome.deco.DecoEBTree.TreeType;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBScree;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBScree;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBScree extends RealisticBiomeEBBase
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
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.OXISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.OXISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.OXISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.OXISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.sandstone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.sandstone);
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
    
	public RealisticBiomeEBScree(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBScree(),
			new SurfaceEBScree(config,
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
		
        this.generatesEmeralds = true;
        this.emeraldEmeraldBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.oreEmeraldEB, Blocks.emerald_ore);
        this.emeraldEmeraldMeta = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
        this.emeraldStoneBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
        this.emeraldStoneMeta = EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0);
        
        DecoEBTree ebTreesCypress = new DecoEBTree();
		ebTreesCypress.strengthFactorForLoops = 3f;
		ebTreesCypress.treeType = TreeType.CYPRESS;
		ebTreesCypress.distribution.noiseDivisor = 80f;
		ebTreesCypress.distribution.noiseFactor = 60f;
		ebTreesCypress.distribution.noiseAddend = -15f;
		ebTreesCypress.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		ebTreesCypress.treeConditionNoise = 5f;
		ebTreesCypress.treeConditionChance = 2;
		ebTreesCypress.maxY = 120;
		
        DecoEBTree ebTreesEucalyptus = new DecoEBTree();
		ebTreesEucalyptus.strengthFactorForLoops = 3f;
		ebTreesEucalyptus.treeType = TreeType.EUCALYPTUS;
		ebTreesEucalyptus.distribution.noiseDivisor = 80f;
		ebTreesEucalyptus.distribution.noiseFactor = 60f;
		ebTreesEucalyptus.distribution.noiseAddend = -15f;
		ebTreesEucalyptus.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		ebTreesEucalyptus.treeConditionNoise = 5f;
		ebTreesEucalyptus.treeConditionChance = 2;
		ebTreesEucalyptus.maxY = 120;
		
        DecoHelper5050 decoHelper5050_1 = new DecoHelper5050(ebTreesCypress, ebTreesEucalyptus);
		this.addDeco(decoHelper5050_1);
        
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
		this.addDeco(decoHelper5050, this.config._boolean(BiomeConfigEBScree.decorationLogsId));        
        
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
