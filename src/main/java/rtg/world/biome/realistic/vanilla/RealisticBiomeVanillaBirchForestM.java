package rtg.world.biome.realistic.vanilla;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestM;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGBirch;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestM;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;

public class RealisticBiomeVanillaBirchForestM extends RealisticBiomeVanillaBase
{
    
    public static BiomeGenBase standardBiome = BiomeGenBase.birchForest;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaBirchForestM(BiomeConfig config)
    {
        
        super(
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaBirchForestM(),
            new SurfaceVanillaBirchForestM(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f));
        
        this.config = config;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
            
            for (int b2 = 0; b2 < 16f * strength; b2++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                WorldGenerator worldgenerator = new WorldGenTreeRTGBirch(16 + rand.nextInt(8), rand.nextInt(8) + 4);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        
        if (TerrainGen.decorate(world, rand, chunkX, chunkY, GRASS)) {
            
            for (int l14 = 0; l14 < 8f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenGrass(Blocks.double_plant, RandomUtil.getRandomInt(rand, 2, 3))).generate(world, rand, l19, k22, j24);
            }
            
            for (int l14 = 0; l14 < 24f * strength; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenGrass(Blocks.tallgrass, rand.nextInt(3))).generate(world, rand, l19, k22, j24);
            }
        }
        
        if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
            
            if (this.config.getPropertyById(BiomeConfigVanillaBirchForestM.decorationLogsId).valueBoolean) {
            
                if (rand.nextInt((int) (16f / strength)) == 0)
                {
                    int x22 = chunkX + rand.nextInt(16) + 8;
                    int z22 = chunkY + rand.nextInt(16) + 8;
                    int y22 = world.getHeightValue(x22, z22);
                    if (y22 < 100)
                    {
                        (new WorldGenLog(Blocks.log, 2, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
                    }
                }
            }
                
            for (int f24 = 0; f24 < 2f * strength; f24++)
            {
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeightValue(i1, j1);
                if (k1 < 110)
                {
                    (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
                }
            }
        }
    }
}
