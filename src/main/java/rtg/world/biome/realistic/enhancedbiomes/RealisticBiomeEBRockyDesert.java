package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRockyDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRockyDesert;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.gen.WorldGenRockSpire;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

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
			new TerrainEBRockyDesert(230f, 60f, 0f),
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
		
		this.config = config;
        
    }
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 80)
            {
                blockBlob.generate(world, rand, i1, k1, j1);
            }
        }
        
        if (river > 0.7f)
        {
            if (river > 0.86f)
            {
                for (int b33 = 0; b33 < 10f * strength; b33++)
                {
                    int j6 = chunkX + rand.nextInt(16) + 8;
                    int k10 = chunkY + rand.nextInt(16) + 8;
                    int z52 = world.getHeightValue(j6, k10);
                    
                    if (z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
                    {
                        WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new WorldGenShrub(0, 0) : TreeGen.eucalyptus_shrub(rand);
                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                        worldgenerator.generate(world, rand, j6, z52, k10);
                    }
                }
            }
            
            for (int f25 = 0; f25 < 2f * strength; f25++)
            {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
            }
            
            for (int f23 = 0; f23 < 3; f23++)
            {
                int j15 = chunkX + rand.nextInt(16) + 8;
                int j17 = rand.nextInt(128);
                int j20 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenFlowers(new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11})).generate(world, rand, j15, j17, j20);
            }
            
            for (int l14 = 0; l14 < 15; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                
                if (rand.nextInt(3) == 0)
                {
                    (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
                }
                else
                {
                    (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
                }
            }
        }
        else {

            if (rand.nextInt(8) == 0)
            {
                int j2 = chunkX + rand.nextInt(16) + 8;
                int j5 = chunkY + rand.nextInt(16) + 8;
       
                int l3 = world.getTopSolidOrLiquidBlock(j2, j5);

                WorldGenerator rockSpire =
                new WorldGenRockSpire(
                    new Block[] { 
                        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone), 
                        (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone, 
                        Blocks.sandstone 
                    }, 
                    new byte[] {
                        EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0), 
                        EBAPI.ebStonify(EBAPI.HARDENED_SANDSTONE, (byte)0), 
                        0
                    },
                    10
                );
                rockSpire.generate(world, rand, j2, l3, j5);
            }
            
            for (int k18 = 0; k18 < 5f * strength; k18++)
            {
                int k21 = chunkX + rand.nextInt(16) + 8;
                int j23 = 64 + rand.nextInt(64);
                int k24 = chunkY + rand.nextInt(16) + 8;
                
                if (j23 < 120f)
                {
                    (new WorldGenCacti(false)).generate(world, rand, k21, j23, k24);
                }
            }
            
            for (int i15 = 0; i15 < 1f * strength; i15++)
            {
                int i17 = chunkX + rand.nextInt(16) + 8;
                int i20 = 64 + rand.nextInt(64);
                int l22 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
            }
        }
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        this.getSurface().paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        
        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
