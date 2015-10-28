package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.gen.WorldGenSpikedBush;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneCanyon;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBScrub;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBScrub;

public class RealisticBiomeEBScrub extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBScrub(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBScrub(),
			new SurfaceEBSandstoneCanyon(
                Blocks.sand, //Block top 
                (byte)0, //byte topByte
                EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt, //Block filler, 
                EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0, //byte fillerByte
                EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt, //Block mixTop, 
                EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0, //byte mixTopByte, 
                EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt, //Block mixFill, 
                EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0, //byte mixFillByte, 
                (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneEB : Blocks.stone, //Block cliff1, 
                (EnhancedBiomesMod.useNewStone == 1) ? (byte)7 : (byte)0, //byte cliff1Byte, 
                (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone, //Block cliff2, 
                (EnhancedBiomesMod.useNewStone == 1) ? (byte)7 : (byte)0, //byte cliff2Byte, 
                90f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.setRealisticBiomeName("EB Scrub");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigEB.weightEBScrub;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int f24 = 0; f24 < 2f * strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 110)
            {
                if (rand.nextInt(4) != 0) {
                    (new WorldGenSpikedBush(Blocks.log, 0, Blocks.leaves, 0, (EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt))).generate(world, rand, i1, k1, j1);
                }
                else {
                    (new WorldGenTreeShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
                }
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
