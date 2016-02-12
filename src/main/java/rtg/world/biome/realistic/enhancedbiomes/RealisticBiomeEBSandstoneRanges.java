package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.enhancedbiomes.config.BiomeConfigEBSandstoneRanges;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneRanges;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneRanges;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeEBSandstoneRanges extends RealisticBiomeEBBase
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
    
	public RealisticBiomeEBSandstoneRanges(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBSandstoneRanges(false, 35f, 160f, 30f, 30f, 60),
			new SurfaceEBSandstoneRanges(config,
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
        
    }
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (l > 5f)
        {
            for (int b2 = 0; b2 < 3f * strength; b2++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (z52 < 120 && rand.nextInt(2) == 0)
                {
                    if (rand.nextBoolean()) {
                        WorldGenerator worldgenerator = TreeGen.cypress(rand);
                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                        worldgenerator.generate(world, rand, j6, z52, k10);
                    }
                    else {
                        WorldGenerator worldgenerator = TreeGen.eucalyptus(rand);
                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                        worldgenerator.generate(world, rand, j6, z52, k10);
                    }
                }
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigEBSandstoneRanges.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (8f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                if (y22 < 100)
                {
                    if (rand.nextInt(8) == 0) {
                        
                        if (rand.nextBoolean()) {
                            (new WorldGenLog(EnhancedBiomesBlocks.logSpruce, 1, EnhancedBiomesBlocks.leavesSpruce, -1, 4 + rand.nextInt(3))).generate(world, rand, x22, y22, z22);
                        }
                        else {
                            (new WorldGenLog(EnhancedBiomesBlocks.logBirch, 1, EnhancedBiomesBlocks.leavesBirch, -1, 4 + rand.nextInt(3))).generate(world, rand, x22, y22, z22);
                        }
                    }
                }
            }
        }
        
        for (int f24 = 0; f24 < 2f * strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 110 && rand.nextInt(2) == 0)
            {
                if (rand.nextBoolean()) {
                    TreeGen.eucalyptus_shrub(rand);
                }
                else {
                    (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
                }
            }
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
        }
    }
}
