package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSahara;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSahara;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.gen.WorldGenRockSpire;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class RealisticBiomeEBSahara extends RealisticBiomeEBBase
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
    private static Block ebMixTopBlock = Blocks.sand;
    private static byte ebMixTopByte = (byte)0;
    private static Block ebMixFillBlock = Blocks.sandstone;
    private static byte ebMixFillByte = (byte)0;
    private static Block ebCliff1Block = Blocks.sandstone;
    private static byte ebCliff1Byte = (byte)0;
    private static Block ebCliff2Block = Blocks.sandstone;
    private static byte ebCliff2Byte = (byte)0;
    
	public RealisticBiomeEBSahara(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBSahara(),
			new SurfaceEBSahara(
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
		
		this.config = config;
		this.generateVillages = ConfigEB.villageEBSahara;
        
    }
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
//        for (int x = 0; x < 16; x++)
//        {
//            int var5 = chunkX + x;
//            int var6 = chunkY + var5 / 3 % 16;
//            
//            new WorldGenDunes(this.baseBiome, Blocks.sand).generate(world, rand, var5, world.getTopSolidOrLiquidBlock(var5, var6), var6);
//        }
                
                
        if (rand.nextInt(3) == 0)
        {
            int j2 = chunkX + rand.nextInt(16) + 8;
            int j5 = chunkY + rand.nextInt(16) + 8;
            
            int l3 = world.getTopSolidOrLiquidBlock(j2, j5);
            
            new WorldGenRockSpire(
                new Block[] {
                    ebDominantStoneBlock[0],
                    ebDominantCobblestoneBlock[0],
                    Blocks.sandstone
                },
                new byte[] {
                    ebDominantStoneMeta[0],
                    ebDominantCobblestoneMeta[0],
                    (byte)0
                },
                10
            ).generate(world, rand, j2, l3, j5);
        }
                
        if (rand.nextInt(500) == 0)
        {
            int var5 = chunkX + rand.nextInt(16) + 8;
            int var6 = chunkY + rand.nextInt(16) + 8;
            WorldGenDesertWells var7 = new WorldGenDesertWells();
            var7.generate(world, rand, var5, world.getHeightValue(var5, var6) + 1, var6);
        }
    }
}
