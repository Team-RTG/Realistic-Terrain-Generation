package rtg.world.biome.realistic.minestrappolation;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.minestrappolation.config.BiomeConfigMSRedwoodForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrubCustom;
import rtg.world.gen.surface.minestrappolation.SurfaceMSRedwoodForest;
import rtg.world.gen.terrain.minestrappolation.TerrainMSRedwoodForest;

public class RealisticBiomeMSRedwoodForest extends RealisticBiomeMSBase
{

    public RealisticBiomeMSRedwoodForest(BiomeGenBase msBiome, BiomeConfig config)
    {
    
        super(config, 
            msBiome,
            BiomeGenBase.river,
            new TerrainMSRedwoodForest(),
            new SurfaceMSRedwoodForest(config, msBiome.topBlock, msBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, msBiome.topBlock, 0.10f));
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (this.config.getPropertyById(BiomeConfigMSRedwoodForest.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (10f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22,0, z22)).getY();
                
                if (y22 < 100)
                {
                    (new WorldGenLog(Blocks.log, 0, Blocks.leaves, -1, 2 + rand.nextInt(2))).generate(world, rand, x22, y22, z22);
                }
            }
        }
        
        for (int f24 = 0; f24 < 3f * strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0,j1)).getY();
            
            if (k1 < 110 && rand.nextInt(3) != 0)
            {
                (new WorldGenTreeRTGShrubCustom(rand.nextInt(4) + 1, Blocks.log, (byte)0, Blocks.leaves, (byte)0)).generate(world, rand, i1, k1, j1);
            }
        }
        
        for (int l14 = 0; l14 < 8f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
