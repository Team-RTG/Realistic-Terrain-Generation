package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBlossomWoods;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBlossomWoods;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBBlossomWoods extends RealisticBiomeEBBase
{
    private static Block ebTopBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.grassEB : Blocks.grass;
    private static byte ebTopByte = EnhancedBiomesMod.useNewGrass ? (byte)0 : (byte)0;
    private static Block ebFillBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt;
    private static byte ebFillByte = EnhancedBiomesMod.useNewGrass ? (byte)0 : (byte)0;
    private static Block ebMixTopBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.grassEB : Blocks.grass;
    private static byte ebMixTopByte = EnhancedBiomesMod.useNewGrass ? (byte)0 : (byte)0;
    private static Block ebMixFillBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt;
    private static byte ebMixFillByte = EnhancedBiomesMod.useNewGrass ? (byte)0 : (byte)0;
    private static Block ebCliff1Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneEB : Blocks.stone;
    private static byte ebCliff1Byte = (EnhancedBiomesMod.useNewStone == 1) ? (byte)4 : (byte)0;
    private static Block ebCliff2Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte ebCliff2Byte = (EnhancedBiomesMod.useNewStone == 1) ? (byte)4 : (byte)0;
    
	public RealisticBiomeEBBlossomWoods(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBBlossomWoods(),
			new SurfaceEBBlossomWoods(
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
		
		this.setRealisticBiomeName("EB Blossom Woods");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBBlossomWoods;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (rand.nextInt((int) (6f / strength)) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            
            if (y22 < 100)
            {
                if (rand.nextBoolean()) {
                    (new WorldGenLog(EnhancedBiomesBlocks.logJungle, 2, EnhancedBiomesBlocks.leavesJungle, -1, 2 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
                }
            }
        }
        
        for (int f23 = 0; f23 < 8f * strength; f23++)
        {
            if (rand.nextInt(32) == 0) {
                int j15 = chunkX + rand.nextInt(16) + 8;
                int j17 = rand.nextInt(128);
                int j20 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenFlowers(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})).generate(world, rand, j15, j17, j20);
            }
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            if (rand.nextInt(32) == 0) {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
            }
        }
    }
}
