package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.WorldGenTreeJungleFat;
import rtg.world.gen.feature.tree.WorldGenTreeJungleTall;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
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
            new TerrainVanillaJungleHills(),
            new SurfaceVanillaJungleHills(Blocks.grass, Blocks.dirt, false, null, 1f, 1.5f, 60f, 65f, 1.5f));
        
        this.setRealisticBiomeName("Vanilla Jungle Hills");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigVanilla.weightVanillaJungleHills;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        if (rand.nextInt((int) (16f / strength)) == 0)
        {
            int i2 = chunkX + rand.nextInt(16) + 8;
            int i8 = chunkY + rand.nextInt(16) + 8;
            int l4 = world.getHeightValue(i2, i8);
            
            if (l4 > 63)
            {
                (new WorldGenLakes(Blocks.water)).generate(world, rand, i2, l4, i8);
            }
        }
        
        for (int b33 = 0; b33 < 3; b33++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            if (z52 < 100 && rand.nextInt(4) == 0)
            {
                WorldGenerator worldgenerator = new WorldGenTreeJungleTall(Blocks.log, 3, Blocks.leaves, 3, 10 + rand.nextInt(10), 2 + rand.nextInt(2), 13f, 4, 0.32f, 0.1f);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
