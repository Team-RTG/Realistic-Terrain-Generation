package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaJungleHills extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.jungleHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.jungleHills.fillerBlock;
    
    public RealisticBiomeVanillaJungleHills()
    {
    
        super(
            BiomeGenBase.jungleHills,
            BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainVanillaJungleHills(230f, 120f, 50f),
            new SurfaceVanillaJungleHills(Blocks.grass, Blocks.dirt, false, null, 1f, 1.5f, 60f, 65f, 1.5f));
        
        this.setRealisticBiomeName("Vanilla Jungle Hills");
        this.biomeCategory = BiomeCategory.NORMAL;
        this.biomeWeight = ConfigVanilla.weightVanillaJungleHills;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        for (int b33 = 0; b33 < 2; b33++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            if (z52 < 100 || rand.nextInt(3) == 0)
            {
                WorldGenerator worldgenerator =
                    rand.nextInt(5) == 0 ? new WorldGenShrub(0, 0) : rand.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 5, 10, 3, 3)
                        : new WorldGenTrees(false, 3 + rand.nextInt(5), 3, 3, true);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        
        for (int l14 = 0; l14 < 15; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextInt(6) == 0)
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
