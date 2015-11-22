package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneCanyon;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneRangesM;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSandstoneRangesM;

public class RealisticBiomeEBSandstoneRangesM extends RealisticBiomeEBBase
{	
	public RealisticBiomeEBSandstoneRangesM(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBSandstoneRangesM(false, 35f, 160f, 40f, 30f, 10),
			new SurfaceEBSandstoneCanyon(
                (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneEB : Blocks.sandstone, //Block top 
                (EnhancedBiomesMod.useNewStone == 1) ? (byte)2 : (byte)0, //byte topByte
                Blocks.sandstone, //Block filler, 
                (byte)0, //byte fillerByte
                EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt, //Block mixTop, 
                EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0, //byte mixTopByte, 
                Blocks.sandstone, //Block mixFill, 
                (byte)0, //byte mixFillByte, 
                (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneEB : Blocks.stone, //Block cliff1, 
                (EnhancedBiomesMod.useNewStone == 1) ? (byte)2 : (byte)0, //byte cliff1Byte, 
                (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone, //Block cliff2, 
                (EnhancedBiomesMod.useNewStone == 1) ? (byte)2 : (byte)0, //byte cliff2Byte, 
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.setRealisticBiomeName("EB Sandstone Ranges M");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSandstoneRangesM;
		this.generateVillages = ConfigEB.villageEBSandstoneRangesM;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
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
                    (new WorldGenTreeShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
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
