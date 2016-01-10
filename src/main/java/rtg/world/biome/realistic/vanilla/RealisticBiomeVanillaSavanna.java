package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.api.biomes.vanilla.config.BiomeConfigVanillaSavanna;
import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeSavanna;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavanna;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavanna;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaSavanna extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.savanna.topBlock;
    public static Block fillerBlock = BiomeGenBase.savanna.fillerBlock;
    
    public RealisticBiomeVanillaSavanna()
    {
    
        super(
            BiomeGenBase.savanna,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainVanillaSavanna(),
            new SurfaceVanillaSavanna(Blocks.grass, Blocks.dirt, Blocks.grass, 13f, 0.27f));
        
        this.biomeConfig = new BiomeConfigVanillaSavanna();
        this.biomeWeight = ConfigVanilla.weightVanillaSavanna;
        this.generateVillages = ConfigVanilla.villageVanillaSavanna;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        for (int i23 = 0; i23 < 1; i23++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (rand.nextInt(8) == 0) {
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
            }
        }
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        if (l > 0f && rand.nextInt(12) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            (new WorldGenLog(Blocks.log2, 0, Blocks.leaves2, -1, 3 + rand.nextInt(3))).generate(world, rand, x22, y22, z22);
        }
        
        if (river > 0.8f)
        {
            for (int b33 = 0; b33 < 15f * strength; b33++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                WorldGenerator worldgenerator =
                    rand.nextInt(3) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(9) == 0 ? new WorldGenTreeSavanna(1)
                        : new WorldGenTreeSavanna(2);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
            
            for (int f25 = 0; f25 < 2f * strength; f25++)
            {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
            }
        }
        else if (simplex.noise2(chunkX / 180f, chunkY / 180f) > 0.20f)
        {
            for (int b33 = 0; b33 < 7f * strength; b33++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (rand.nextInt(9) == 0) {
                    WorldGenerator worldgenerator = new WorldGenShrub(0, 0);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
                
                if (rand.nextInt(9) == 0) {
                    WorldGenerator worldgenerator = new WorldGenTreeSavanna(1);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
                
                if (rand.nextInt(9) == 0) {
                    WorldGenerator worldgenerator = new WorldGenTreeSavanna(2);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
            }
        }
        else
        {
            int a = 3 - (int) (simplex.noise2(chunkX / 100f, chunkY / 100f) * 7);
            if (a < 1 || rand.nextInt(a) == 0)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                WorldGenerator worldgenerator =
                    rand.nextInt(3) == 0 ? new WorldGenShrub(0, 0) : rand.nextInt(5) == 0 ? new WorldGenTreeSavanna(0)
                        : new WorldGenTreeSavanna(1);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        
        for (int f23 = 0; f23 < 3; f23++)
        {
            int j15 = chunkX + rand.nextInt(16) + 8;
            int j17 = rand.nextInt(128);
            int j20 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenFlowers(new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11})).generate(world, rand, j15, j17, j20);
        }
        
        for (int l14 = 0; l14 < 20; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextInt(3) == 0)
            {
                (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
            }
            else
            {
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
            }
        }
    }
}
