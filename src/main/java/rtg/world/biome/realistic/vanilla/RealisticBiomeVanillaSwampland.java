package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.util.BlockPos;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSwampland;
import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.feature.tree.WorldGenTreeRTGWillow;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSwampland;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSwampland;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaSwampland extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.swampland.topBlock.getBlock();
    public static Block fillerBlock = BiomeGenBase.swampland.fillerBlock.getBlock();
    
    public RealisticBiomeVanillaSwampland(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.swampland,
            BiomeGenBase.river,
            new TerrainVanillaSwampland(),
            new SurfaceVanillaSwampland(topBlock, fillerBlock));
        
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
            if (rand.nextInt(2) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getChunkFromBlockCoords(new BlockPos(j6, 1, k10)).getHeightValue(j6,k10);
                
                if (z52 < 110)
                {
                    WorldGenerator worldgenerator = new WorldGenTreeRTGWillow();

                    worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                }
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaSwampland.decorationLogsId).valueBoolean) {
        
            if (rand.nextInt((int) (4f / strength)) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getChunkFromBlockCoords(new BlockPos(x22, 1, z22)).getHeightValue(x22,z22);
                if (y22 < 100)
                {
                    (new WorldGenLog(Blocks.log2, 1, Blocks.leaves2, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }
        
        for (int f24 = 0; f24 < 3f * strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getChunkFromBlockCoords(new BlockPos(i1, 1, j1)).getHeightValue(i1,j1);
            if (k1 < 110)
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
        if (rand.nextInt((int) (50f / strength)) == 0)
        {
            int j16 = chunkX + rand.nextInt(16) + 8;
            int j18 = rand.nextInt(100);
            int j21 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
        }
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
