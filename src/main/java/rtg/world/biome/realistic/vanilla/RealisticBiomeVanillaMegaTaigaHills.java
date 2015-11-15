package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.feature.tree.WorldGenTreeSprucePineBig;
import rtg.world.gen.feature.tree.WorldGenTreeSpruceSmall;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMegaTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMegaTaigaHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaMegaTaigaHills extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.megaTaigaHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.megaTaigaHills.fillerBlock;
    
    public RealisticBiomeVanillaMegaTaigaHills()
    {
    
        super(
            BiomeGenBase.megaTaigaHills,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainVanillaMegaTaigaHills(),
            new SurfaceVanillaMegaTaigaHills(Blocks.grass, Blocks.dirt, true, Blocks.sand, 0.2f));
        
        this.setRealisticBiomeName("Vanilla Mega Taiga Hills");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigVanilla.weightVanillaMegaTaigaHills;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
        
        // boulders
        for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.mossy_cobblestone, 0, rand)).generate(world, rand, i1, k1, j1);
            }
        }
        
        // trees
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        for (int b1 = 0; b1 < l * 4f * strength; b1++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            WorldGenerator worldgenerator =
                rand.nextInt(4) == 0 ? new WorldGenTreeSpruceSmall(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? new WorldGenTreeSpruceSmall(
                    1 + rand.nextInt(3)) : new WorldGenTreeSprucePineBig(4 + rand.nextInt(6), 12 + rand.nextInt(10));
            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(world, rand, j6, z52, k10);
        }
        
        if (l > 0f && rand.nextInt(6) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);
        }
        
        for (int b = 0; b < 2f * strength; b++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            if (rand.nextInt(10) == 0)
            {
                (new WorldGenTreeShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
            }
            else
            {
                (new WorldGenTreeShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
            }
        }
        
        if (rand.nextInt((int) (3f / strength)) == 0)
        {
            int k15 = chunkX + rand.nextInt(16) + 8;
            int k17 = rand.nextInt(64) + 64;
            int k20 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextBoolean())
            {
                (new WorldGenFlowers(Blocks.brown_mushroom)).generate(world, rand, k15, k17, k20);
            }
            else
            {
                (new WorldGenFlowers(Blocks.red_mushroom)).generate(world, rand, k15, k17, k20);
            }
        }
        
        if (rand.nextInt((int) (20f / strength)) == 0)
        {
            int j16 = chunkX + rand.nextInt(16) + 8;
            int j18 = rand.nextInt(128);
            int j21 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
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
