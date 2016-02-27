package rtg.world.biome.realistic.abyssalcraft;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrubCustom;
import rtg.world.gen.feature.tree.abyssalcraft.WorldGenTreeACDarkwood;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsForest;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsForest;

import java.util.Random;

public class RealisticBiomeACDarklandsForest extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklandsForest(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsForest(),
            new SurfaceACDarklandsForest(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, acBiome.topBlock, 0.10f));
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

            if (rand.nextInt(3) == 0) {
                
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();
                
                WorldGenerator worldgenerator = new WorldGenTreeACDarkwood(6 + rand.nextInt(6), 10 + rand.nextInt(10), 0, 0);
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigACDarklandsForest.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (10f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22,0, z22)).getY();
                
                if (y22 < 100)
                {
                    (new WorldGenLog(ACBlocks.darklands_oak_wood, 0, ACBlocks.darklands_oak_leaves, -1, 2 + rand.nextInt(2))).generate(world, rand, x22, y22, z22);
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
                (new WorldGenTreeRTGShrubCustom(rand.nextInt(4) + 1, ACBlocks.darklands_oak_wood, (byte)0, ACBlocks.darklands_oak_leaves, (byte)0)).generate(world, rand, i1, k1, j1);
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
