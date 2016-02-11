package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSpruceSmall;
import rtg.world.gen.surface.highlands.SurfaceHLWindyIsland;
import rtg.world.gen.terrain.highlands.TerrainHLWindyIsland;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeHLWindyIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.windyIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLWindyIsland(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeGenBase.river,
            new TerrainHLWindyIsland(),
            new SurfaceHLWindyIsland(config, topBlock, fillerBlock));
        
        this.config = config;
    }
    
    // this is the Extreme Hills code again, with plain boulders, smaller trees, no pumpkins and no flowers
        @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        // boulders
        for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);

            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.cobblestone, 0, rand)).generate(world, rand, i1, k1, j1);
            }
        }

        // trees
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        for (int b1 = 0; b1 < l * 4f * strength; b1++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);

            if (rand.nextInt(24) == 0) {
                WorldGenerator worldgenerator = new WorldGenTreeRTGSpruceSmall(0 + rand.nextInt(1));
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }


        for (int b = 0; b < 2f * strength; b++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            if (rand.nextInt(10) == 0)
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
            }
            else
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
            }
        }

        for (int l14 = 0; l14 < 10f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
        }
    }
}
