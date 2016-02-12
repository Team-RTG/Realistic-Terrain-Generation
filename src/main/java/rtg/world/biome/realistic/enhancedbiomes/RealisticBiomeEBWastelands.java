package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWastelands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWastelands;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

public class RealisticBiomeEBWastelands extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.GABBRO, (byte)0),
        EBAPI.ebStonify(EBAPI.BASALT, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.GABBRO, (byte)0),
        EBAPI.ebStonify(EBAPI.BASALT, (byte)0)
    };
    
    private static Block cobbleBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone);
    private static byte cobbleByte = EBAPI.ebStonify(EBAPI.GABBRO, (byte)0);
    
	public RealisticBiomeEBWastelands(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBWastelands(),
			new SurfaceEBWastelands(config, 
                Blocks.gravel, //Block top 
                (byte)0, //byte topByte
                cobbleBlock, //Block filler, 
                cobbleByte, //byte fillerByte
                Blocks.dirt, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                cobbleBlock, //Block mixFill, 
                cobbleByte, //byte mixFillByte, 
                cobbleBlock, //Block cliff1, 
                cobbleByte, //byte cliff1Byte, 
                cobbleBlock, //Block cliff2, 
                cobbleByte, //byte cliff2Byte, 
                40f, //float mixWidth, 
                -0.10f, //float mixHeight, 
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
    
        for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 95 && (k1 < 64 || rand.nextInt(8) == 0))
            {
                (new WorldGenBlob(cobbleBlock, cobbleByte, rand.nextInt(1), rand)).generate(world, rand, i1, k1, j1, true);
            }
        }
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        for (int i15 = 0; i15 < 1f * strength; i15++)
        {
            int i17 = chunkX + rand.nextInt(16) + 8;
            int i20 = 64 + rand.nextInt(64);
            int l22 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextInt(16) == 0) {
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
            }
        }
    }
}
