package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoEBTree;
import rtg.world.biome.deco.DecoEBTree.TreeType;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBPolarDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBPolarDesert;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBPolarDesert extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.RHYOLITE, (byte)0),
        EBAPI.ebStonify(EBAPI.DACITE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.RHYOLITE, (byte)0),
        EBAPI.ebStonify(EBAPI.DACITE, (byte)0)
    };
    
    private static Block ebTopBlock = EBAPI.ebGrassify(Blocks.snow, Blocks.snow);
    private static byte ebTopByte = EBAPI.ebGrassify((byte)0, (byte)0);
    private static Block ebFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebFillByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebMixTopBlock = EBAPI.ebGrassify(Blocks.snow, Blocks.snow);
    private static byte ebMixTopByte = EBAPI.ebGrassify((byte)0, (byte)0);
    private static Block ebMixFillBlock = EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt);
    private static byte ebMixFillByte = EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0);
    private static Block ebCliff1Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone);
    private static byte ebCliff1Byte = EBAPI.ebStonify(EBAPI.RHYOLITE, (byte)0);
    private static Block ebCliff2Block = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone);
    private static byte ebCliff2Byte = EBAPI.ebStonify(EBAPI.RHYOLITE, (byte)0);
    
    public RealisticBiomeEBPolarDesert(BiomeGenBase ebBiome, BiomeConfig config)
    {
    
        super(config, 
            ebBiome, BiomeGenBase.frozenRiver,
            new TerrainEBPolarDesert(),
            new SurfaceEBPolarDesert(config, 
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
        noWaterFeatures = true;
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.packed_ice;
		decoBoulder.checkRiver = true;
		decoBoulder.minRiver = 0.86f;
		decoBoulder.strengthFactor = 5f;
		decoBoulder.chance = 16;
		decoBoulder.maxY = 64;
		this.addDeco(decoBoulder);
        
        DecoEBTree ebTrees = new DecoEBTree();
		ebTrees.treeType = TreeType.POLAR_DESERT;
		ebTrees.checkRiver = true;
		ebTrees.minRiver = 0.86f; 
		ebTrees.distribution.noiseDivisor = 80f;
		ebTrees.distribution.noiseFactor = 60f;
		ebTrees.distribution.noiseAddend = -15f;
		ebTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		ebTrees.treeConditionChance = 8;
		ebTrees.maxY = 120;
		this.addDeco(ebTrees);        
    }
}
