package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBEphemeralLakeEdge;
import rtg.world.biome.deco.DecoEBTree;
import rtg.world.biome.deco.DecoEBTree.TreeType;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBEphemeralLakeEdge;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBEphemeralLakeEdge;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBEphemeralLakeEdge extends RealisticBiomeEBBase
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
    
    private static Block ebTopBlock = EBAPI.ebGrassify(Blocks.sand, Blocks.sand);
    private static byte ebTopByte = EBAPI.ebGrassify((byte)0, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.HISTOSOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass);
    private static byte ebMixTopByte = EBAPI.ebGrassify(EBAPI.HISTOSOL, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.HISTOSOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(Blocks.stone, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify((byte)0, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(Blocks.cobblestone, Blocks.cobblestone);
    private static byte ebCliff2Byte = EBAPI.ebStonify((byte)0, (byte)0);
    
	public RealisticBiomeEBEphemeralLakeEdge(BiomeGenBase ebBiome, BiomeConfig config)
	{
        super(config, 
            ebBiome, BiomeGenBase.river,
            new TerrainEBEphemeralLakeEdge(),
            new SurfaceEBEphemeralLakeEdge(config,
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
		ebTrees.strengthNoiseFactorForLoops = true;
		ebTrees.treeType = TreeType.DEAD;
		ebTrees.distribution.noiseDivisor = 80f;
		ebTrees.distribution.noiseFactor = 60f;
		ebTrees.distribution.noiseAddend = -15f;
		ebTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		ebTrees.maxY = 110;
		this.addDeco(ebTrees);        
        
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree.logConditionNoise = 8f;
        decoFallenTree.logConditionChance = 3;
        decoFallenTree.maxY = 100;
        decoFallenTree.logBlock = EnhancedBiomesBlocks.logJungle;
        decoFallenTree.logMeta = (byte)1;
        decoFallenTree.leavesBlock = EnhancedBiomesBlocks.leavesJungle;
        decoFallenTree.leavesMeta = (byte)-1;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;        
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigEBEphemeralLakeEdge.decorationLogsId));
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.chance = 4;
        decoShrub.strengthFactor = 3f;
		this.addDeco(decoShrub);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
