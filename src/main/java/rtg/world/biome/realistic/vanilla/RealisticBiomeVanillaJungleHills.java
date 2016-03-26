package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleHills;
import rtg.util.math.RandomUtil;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.*;
import rtg.world.gen.feature.tree.WorldGenTreeRTGMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPalmCustom;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleHills;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.*;

public class RealisticBiomeVanillaJungleHills extends RealisticBiomeVanillaBase
{
    
    public static IBlockState topBlock = Biomes.jungleHills.topBlock;
    public static IBlockState fillerBlock = Biomes.jungleHills.fillerBlock;
    
    public RealisticBiomeVanillaJungleHills(BiomeConfig config)
    {
    
        super(config, 
            Biomes.jungleHills,
            Biomes.river,
            new TerrainVanillaJungleHills(),
            new SurfaceVanillaJungleHills(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), false, null, 1f, 1.5f, 60f, 65f, 1.5f));
        
        this.waterSurfaceLakeChance = 3;
        this.noLakes=true;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 1, chunkY), simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 5f + 0.8f;
        
        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), TREE)) {
        
            for (int b33 = 0; b33 < 5; b33++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 1, k10)).getY();
                
                if (z52 < 100 && rand.nextBoolean())
                {
                    WorldGenerator worldgenerator =
                        rand.nextInt(3) != 0
                        ? new WorldGenMegaJungle(false, 10 + rand.nextInt(18), 20, Blocks.log.getStateFromMeta(3), Blocks.leaves.getStateFromMeta(3))
                        : new WorldGenTreeRTGMangrove(Blocks.log, 3, Blocks.leaves, 3, 10 + rand.nextInt(18), 3 + rand.nextInt(2), 13f, RandomUtil.getRandomInt(rand, 4, 5),
                        0.32f,
                        0.2f);
                    worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                }
                
                if (rand.nextInt(3) == 0) {
                    
                    int j61 = chunkX + rand.nextInt(16) + 8;
                    int k101 = chunkY + rand.nextInt(16) + 8;
                    int z521 = world.getHeight(new BlockPos(j61, 1, k101)).getY();

                    WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(10 + rand.nextInt(11)));
                    worldgenerator.generate(world, rand, new BlockPos(j61, z521, k101));
                }
                
                if (rand.nextInt(3) == 0) {
                    
                    int j61 = chunkX + rand.nextInt(16) + 8;
                    int k101 = chunkY + rand.nextInt(16) + 8;
                    int z521 = world.getHeight(new BlockPos(j61, 1, k101)).getY();

                    int megaHeight = (rand.nextInt(40) == 0) ? (40 + rand.nextInt(20)) : 20 + rand.nextInt(20);
                    
                    WorldGenerator worldgenerator = new WorldGenMegaJungle(false, megaHeight, 0, Blocks.log.getStateFromMeta(3), Blocks.leaves.getStateFromMeta(3));
                    worldgenerator.generate(world, rand, new BlockPos(j61, z521, k101));
                }
                
                
            }

            if (this.config.getPropertyById(BiomeConfigVanillaJungleHills.decorationLogsId).valueBoolean) {
            
                if (l > 0f && rand.nextInt(3) == 0)
                {
                    int x22 = chunkX + rand.nextInt(16) + 8;
                    int z22 = chunkY + rand.nextInt(16) + 8;
                    int y22 = world.getHeight(new BlockPos(x22, 1, z22)).getY();
                    (new WorldGenLog(Blocks.log, 3, Blocks.leaves, -1, 4 + rand.nextInt(5))).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }
        
        if (rand.nextInt(6) != 0) {
            rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        }
        else {
            rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 1, chunkY), simplex, cell, strength, river, baseBiome);
        }
        
        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), LILYPAD)) {
            
            for (int b33 = 0; b33 < 5; b33++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 1, k10)).getY();
                
                for (int h44 = 0; h44 < 8; h44++) {
                    
                    if (z52 > 64) {
                        
                        WorldGenerator worldgenerator2 = new WorldGenWaterlily();
                        worldgenerator2.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                }
                
                for (int h44 = 0; h44 < 100; h44++) {
                    WorldGenerator worldgenerator4 = new WorldGenVines();
                    worldgenerator4.generate(world, rand, new BlockPos(j6, z52, k10));
                }
            }
        }

        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), GRASS)) {
            
            for (int l14 = 0; l14 < 16f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                int grassMeta;
                
                if (rand.nextInt(8) == 0) {
                    grassMeta = 0;
                }
                else {
                    grassMeta = RandomUtil.getRandomInt(rand, 1, 2);
                }
                
                (new WorldGenGrass(Blocks.tallgrass, grassMeta)).generate(world, rand, new BlockPos(l19, k22, j24));
                
                for (int h44 = 0; h44 < 4 && k22 > 63; h44++) {
                    WorldGenerator worldgenerator4 = new WorldGenVines();
                    worldgenerator4.generate(world, rand, new BlockPos(l19, k22, j24));
                }
            }
            
            for (int l14 = 0; l14 < 12f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                
                if (rand.nextInt(5) == 0) {
                    (new WorldGenGrass(Blocks.double_plant, RandomUtil.getRandomInt(rand, 2, 3))).generate(world, rand, new BlockPos(l19, k22, j24));
                }
            }
            
            for (int l14 = 0; l14 < 16f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                int grassMeta;
                
                if (rand.nextInt(8) == 0) {
                    grassMeta = 0;
                }
                else {
                    grassMeta = RandomUtil.getRandomInt(rand, 1, 2);
                }
                
                (new WorldGenGrass(Blocks.tallgrass, grassMeta)).generate(world, rand, new BlockPos(l19, k22, j24));
                
                if (k22 > 63) {
                    
                    for (int h44 = 0; h44 < 8; h44++) {
                        WorldGenerator worldgenerator4 = new WorldGenVines();
                        worldgenerator4.generate(world, rand, new BlockPos(l19, k22, j24));
                    }
                }
            }
        }
        
        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), FLOWERS)) {
            
            for (int f23 = 0; f23 < 2f * strength; f23++)
            {
                int j15 = chunkX + rand.nextInt(16) + 8;
                int j20 = chunkY + rand.nextInt(16) + 8;
                int j17 = world.getHeight(new BlockPos(j15, 1, j20)).getY();
                
                if (rand.nextInt(4) == 0) {
                    
                    (new WorldGenFlowersRTG(new int[] {5})).generate(world, rand, new BlockPos(j15, j17, j20));
                }
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaJungleHills.decorationCactusId).valueBoolean) {
            
            if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), CACTUS)) {
                
                for (int k18 = 0; k18 < 8f * strength; k18++)
                {
                    int k21 = chunkX + rand.nextInt(16) + 8;
                    int j23 = rand.nextInt(160);
                    int k24 = chunkY + rand.nextInt(16) + 8;
                    
                    if (j23 < 120f)
                    {
                        (new WorldGenJungleCacti(false, rand.nextInt(7), (byte)1)).generate(world, rand, new BlockPos(k21, j23, k24));
                    }
                }
            }
        }
        
        for (int l1 = 0; l1 < 2f * strength; ++l1)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 1, j1)).getY();
            
            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.mossy_cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
    }
}
