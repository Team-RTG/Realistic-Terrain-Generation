package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBBlossomHills;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBlossomHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBlossomHills;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBBlossomHills extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.SLATE, (byte)0),
        EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.SLATE, (byte)0),
        EBAPI.ebStonify(EBAPI.DOLOMITE, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebTopByte = EBAPI.ebGrassify(EBAPI.ALFISOL, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.ALFISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.ALFISOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.ALFISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.SLATE, (byte)0);
    private static Block ebCliff2Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.SLATE, (byte)0);
    
	public RealisticBiomeEBBlossomHills(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBBlossomHills(76f, 35f),
			new SurfaceEBBlossomHills(config,
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
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
                
		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.loops = 1;
		decoFallenTree.distribution.noiseDivisor = 80f;
		decoFallenTree.distribution.noiseFactor = 60f;
		decoFallenTree.distribution.noiseAddend = -15f;
		decoFallenTree.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
		decoFallenTree.logConditionNoise = 6f;
		decoFallenTree.logConditionChance = 1;
		decoFallenTree.maxY = 100;
		decoFallenTree.logBlock = EnhancedBiomesBlocks.logJungle;
		decoFallenTree.logMeta = (byte)2;
		decoFallenTree.leavesBlock = EnhancedBiomesBlocks.leavesJungle;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 2;
		decoFallenTree.maxSize = 5;
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigEBBlossomHills.decorationLogsId));
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.chance = 32;
		decoFlowersRTG.strengthFactor = 8f;
        this.addDeco(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.chance = 32;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
