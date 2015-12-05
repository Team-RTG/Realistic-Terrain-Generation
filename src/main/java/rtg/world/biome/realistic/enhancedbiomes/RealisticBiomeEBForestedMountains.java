package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBForestedMountains;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBForestedMountains;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

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
    
	public RealisticBiomeEBForestedMountains(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBForestedMountains(440f, 70f, 0f),
			new SurfaceEBForestedMountains(
			    EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass), //Block top 
			    EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0), //byte topByte
			    EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt), //Block filler, 
			    EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0), //byte fillerByte
			    EBAPI.ebGrassify(EnhancedBiomesBlocks.grassEB, Blocks.grass), //Block mixTop, 
                EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0), //byte mixTopByte, 
                EBAPI.ebGrassify(EnhancedBiomesBlocks.dirtEB, Blocks.dirt), //Block mixFill, 
                EBAPI.ebGrassify(EBAPI.INCEPTISOL, (byte)0), //byte mixFillByte, 
                EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone), //Block cliff1, 
                EBAPI.ebStonify(EBAPI.CHERT, (byte)0), //byte cliff1Byte, 
                (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone, //Block cliff2, 
                EBAPI.ebStonify(EBAPI.CHERT, (byte)0), //byte cliff2Byte, 
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.setRealisticBiomeName("EB Forested Mountains");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBForestedMountains;
		this.generateVillages = ConfigEB.villageEBForestedMountains;
        
    }
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int b1 = 0; b1 < 2 * strength; b1++)
        {
            if (rand.nextInt(2) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (z52 < 230)
                {
                    if (rand.nextInt(32) == 0) {
                        WorldGenerator worldgenerator = TreeGen.greatOak(rand);
                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                        worldgenerator.generate(world, rand, j6, z52, k10);
                    }
                    else {
                        WorldGenerator worldgenerator = TreeGen.birch();
                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                        worldgenerator.generate(world, rand, j6, z52, k10);
                    }
                }
            }
        }
        
        if (l > 5f)
        {
            for (int b2 = 0; b2 < 4f * strength; b2++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (z52 < 220)
                {
                    WorldGenerator worldgenerator =
                        rand.nextInt(2) == 0 ? TreeGen.birch()
                            : rand.nextInt(10) != 0 ? new WorldGenTrees(false) : new WorldGenForest(false, false);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
            }
        }
        
        if (rand.nextInt((int) (32f / strength)) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            if (y22 < 100)
            {
                if (rand.nextBoolean()) {
                    (new WorldGenLog(Blocks.log, 0, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
                }
                else {
                    (new WorldGenLog(Blocks.log, 2, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
                }
            }
        }
        
        for (int f24 = 0; f24 < 3f * strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            if (k1 < 110)
            {
                (new WorldGenTreeShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
            }
        }
        
        for (int f23 = 0; f23 < 8f * strength; f23++)
        {
            int j15 = chunkX + rand.nextInt(16) + 8;
            int j17 = rand.nextInt(128);
            int j20 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenFlowers(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})).generate(world, rand, j15, j17, j20);
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
