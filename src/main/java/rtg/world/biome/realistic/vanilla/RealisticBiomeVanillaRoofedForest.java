package rtg.world.biome.realistic.vanilla;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.gen.GeneratorBushFeature;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForest;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRoofedForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRoofedForest;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.roofedForest.topBlock.getBlock();
    public static Block fillerBlock = BiomeGenBase.roofedForest.fillerBlock.getBlock();
    
    public RealisticBiomeVanillaRoofedForest(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.roofedForest,
            BiomeGenBase.river,
            new TerrainVanillaRoofedForest(),
            new SurfaceVanillaRoofedForest(Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.08f));
        
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
        //float l = simplex.noise3(chunkX / 80f, chunkY / 80f, simplex.noise2(chunkX / 60f, chunkY / 60f)) * 60f - 15f;

        for (int l1 = 0; l1 < 2f * strength; ++l1)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1,1,j1)).getY();
            
            if (k1 < 80 && rand.nextInt(20) == 0) {
                if (rand.nextInt(8) != 0) {
                    (new WorldGenBlob(Blocks.mossy_cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
                }
                else {
                    if (config._boolean(BiomeConfigVanillaRoofedForest.decorationCobwebsId)) {
                        (new WorldGenBlob(Blocks.web, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
                    }
                }
            }
        }
        
        if (l > 0f)
        {
            for (int b2 = 0; b2 < 24f * strength; b2++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6,1,k10)).getY();
                
                if (z52 < 120)
                {
                    WorldGenerator worldgenerator = new WorldGenTreeRTGMangrove(
                        Blocks.log2, 1, Blocks.leaves2, 1, 9 + rand.nextInt(5), 3 + rand.nextInt(2), 13f, 3, 0.32f, 0.1f
                    );

                    worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                }
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaRoofedForest.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (10f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22,1,z22)).getY();
                if (y22 < 100)
                {
                    (new WorldGenLog(Blocks.log2, 1, Blocks.leaves2, -1, 9 + rand.nextInt(5))).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }
        
        for (int f24 = 0; f24 < strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1,1,j1)).getY();
            if (k1 < 110)
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX * 16, 0 ,chunkY * 16), GRASS)) {
            
            for (int l14 = 0; l14 < 10f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                int grassMeta;
                
                if (rand.nextInt(16) == 0) {
                    grassMeta = 0;
                }
                else {
                    grassMeta = 1;
                }
                
                (new WorldGenGrass(Blocks.tallgrass, grassMeta)).generate(world, rand, new BlockPos(l19, k22, j24));
            }
            
            for (int l14 = 0; l14 < 8f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                
                if (rand.nextInt(6) == 0) {
                    (new WorldGenGrass(Blocks.double_plant, RandomUtil.getRandomInt(rand, 2, 3))).generate(world, rand, new BlockPos(l19, k22, j24));
                }
            }
            
            for (int l14 = 0; l14 < 4f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                int grassMeta;
                
                if (rand.nextInt(16) == 0) {
                    grassMeta = 0;
                }
                else {
                    grassMeta = RandomUtil.getRandomInt(rand, 1, 2);
                }
                
                (new WorldGenGrass(Blocks.tallgrass, grassMeta)).generate(world, rand, new BlockPos(l19, k22, j24));
            }
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        int k15 = chunkX + rand.nextInt(16) + 8;
        int k20 = chunkY + rand.nextInt(16) + 8;
        int k17 = world.getHeight(new BlockPos(k15,1,k20)).getY();
        
        if (rand.nextBoolean())
        {
            (new GeneratorBushFeature(Blocks.brown_mushroom)).generate(world, rand, new BlockPos(k15, k17, k20));
        }
        else
        {
            (new GeneratorBushFeature(Blocks.red_mushroom)).generate(world, rand, new BlockPos(k15, k17, k20));
        }
    }
}
