package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHillsPlus;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineEuro;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsPlus;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsPlus;

import java.util.Random;

public class RealisticBiomeVanillaExtremeHillsPlus extends RealisticBiomeVanillaBase
{
    
    public static IBlockState topBlock = BiomeGenBase.extremeHillsPlus.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.extremeHillsPlus.fillerBlock;
    
    public RealisticBiomeVanillaExtremeHillsPlus(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.extremeHillsPlus,
            BiomeGenBase.river,
            new TerrainVanillaExtremeHillsPlus(230f, 120f, 0f),
            new SurfaceVanillaExtremeHillsPlus(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.gravel.getDefaultState(), 0.08f));
        this.generatesEmeralds = true;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
    
        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            
            if (k1 < 80)
            {
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        
        if (this.config.getPropertyById(BiomeConfigVanillaExtremeHillsPlus.decorationLogsId).valueBoolean) {
        
            if (l > 0f && rand.nextInt(6) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, new BlockPos(x22, y22, z22));
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
                    int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
                    
                    if (z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
                    {
                        WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()) : new WorldGenTreeRTGPineEuro();
                        worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
            
            for (int f25 = 0; f25 < 2f * strength; f25++)
            {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, new BlockPos(i18, 60 + rand.nextInt(8), i23));
            }
            
            if (rand.nextInt(28) == 0)
            {
                int j16 = chunkX + rand.nextInt(16) + 8;
                int j18 = rand.nextInt(128);
                int j21 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
            }
            
            for (int f23 = 0; f23 < 3; f23++)
            {
                int j15 = chunkX + rand.nextInt(16) + 8;
                int j17 = rand.nextInt(128);
                int j20 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenFlowers(new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11})).generate(world, rand, new BlockPos(j15, j17, j20));
            }
            
            for (int l14 = 0; l14 < 15; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                
                if (rand.nextInt(3) == 0)
                {
                    (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, new BlockPos(l19, k22, j24));
                }
                else
                {
                    (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
                }
            }
        }
        else
        {
            int a = 3 - (int) (simplex.noise2(chunkX / 100f, chunkY / 100f) * 10);
            if (a < 1 || rand.nextInt(a) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
                
                if (z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
                {
                    WorldGenerator worldgenerator = rand.nextInt(14) != 0 ? new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()) : new WorldGenTreeRTGPineEuro();
                    worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                }
            }
            
            if (rand.nextInt((int) (3f / strength)) == 0)
            {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, new BlockPos(i18, 60 + rand.nextInt(8), i23));
            }
            
            if (rand.nextInt(28) == 0)
            {
                int j16 = chunkX + rand.nextInt(16) + 8;
                int j18 = rand.nextInt(128);
                int j21 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
            }
            
            for (int f23 = 0; f23 < 3; f23++)
            {
                int j15 = chunkX + rand.nextInt(16) + 8;
                int j17 = rand.nextInt(128);
                int j20 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenFlowers(new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11})).generate(world, rand, new BlockPos(j15, j17, j20));
            }
            
            for (int l14 = 0; l14 < 15; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                
                if (rand.nextInt(3) == 0)
                {
                    (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, new BlockPos(l19, k22, j24));
                }
                else
                {
                    (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
                }
            }
        }
    }
}
