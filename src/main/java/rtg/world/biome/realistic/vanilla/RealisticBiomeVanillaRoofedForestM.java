package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForestM;
import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRoofedForestM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRoofedForestM;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaRoofedForestM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.roofedForest;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaRoofedForestM(BiomeConfig config)
    {
    
        super(
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaRoofedForestM(),
            new SurfaceVanillaRoofedForestM(topBlock, fillerBlock));
        
        this.config = config;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        for (int b1 = 0; b1 < l * strength; b1++)
        {
            if (rand.nextInt(1) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (z52 < 110)
                {
                    WorldGenerator worldgenerator =
                        new WorldGenTreeMangrove(Blocks.log2, 1, Blocks.leaves2, 1, 9 + rand.nextInt(5), 3 + rand.nextInt(2), 13f, 3,
                            0.32f,
                            0.1f);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
            }
        }
        
        if (l > 5f)
        {
            for (int b2 = 0; b2 < 3f * strength; b2++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (z52 < 120)
                {
                    WorldGenerator worldgenerator =
                        rand.nextInt(1) == 0 ? new WorldGenTreeMangrove(
                            Blocks.log2, 1, Blocks.leaves2, 1, 9 + rand.nextInt(5), 3 + rand.nextInt(2), 13f, 3, 0.32f, 0.1f
                            )
                            : new WorldGenTrees(false);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaRoofedForestM.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (12f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                if (y22 < 100)
                {
                    (new WorldGenLog(Blocks.log2, 1, Blocks.leaves2, -1, 9 + rand.nextInt(5))).generate(world, rand, x22, y22, z22);
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

        if (rand.nextInt((int) (15f / strength)) == 0)
        {
            int j16 = chunkX + rand.nextInt(16) + 8;
            int j18 = rand.nextInt(100);
            int j21 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
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
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
