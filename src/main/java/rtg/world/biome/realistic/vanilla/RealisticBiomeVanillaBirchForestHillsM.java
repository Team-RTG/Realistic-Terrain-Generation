package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.WorldGenWildWheat;
import rtg.world.gen.feature.tree.WorldGenTreeBirch;
import rtg.world.gen.feature.tree.WorldGenTreeBirchSmall;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaBirchForestHillsM extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.birchForestHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.birchForestHills.fillerBlock;
	
	public RealisticBiomeVanillaBirchForestHillsM()
	{
		super(
			BiomeGenBase.birchForestHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainVanillaBirchForestHills(),
			new SurfaceVanillaBirchForestHills(Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.stone, 0.15f)
		);
		
		this.setRealisticBiomeName("Vanilla Birch Forest Hills");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigVanilla.weightVanillaBirchForestHills;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        if (l > 5f)
        {
            for (int b2 = 0; b2 < 3f * strength; b2++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (z52 < 120)
                {
                    WorldGenerator worldgenerator =
                        rand.nextInt(1) == 0 ? new WorldGenTreeBirchSmall(4 + rand.nextInt(7), 8 + rand.nextInt(12), 2)
                        : rand.nextInt(10) != 0 ? new WorldGenTreeBirch(4 + rand.nextInt(7), 8 + rand.nextInt(12))
                        : new WorldGenForest(false, false);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
            }
        }
        
        if (rand.nextInt((int) (24f / strength)) == 0)
        {
            int x22 = chunkX + rand.nextInt(16) + 8;
            int z22 = chunkY + rand.nextInt(16) + 8;
            int y22 = world.getHeightValue(x22, z22);
            if (y22 < 100)
            {
                (new WorldGenLog(Blocks.log, 2, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, x22, y22, z22);
            }
        }
        
        for (int f24 = 0; f24 < 3f * strength; f24++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            if (k1 < 110)
            {
                (new WorldGenTreeShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
            }
        }
        
        if (rand.nextInt((int) (150f / strength)) == 0)
        {
            int k21 = chunkX + rand.nextInt(16) + 8;
            int j23 = rand.nextInt(60) + 60;
            int k24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
        }
        
        for (int f23 = 0; f23 < 8f * strength; f23++)
        {
            int j15 = chunkX + rand.nextInt(16) + 8;
            int j17 = rand.nextInt(128);
            int j20 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenFlowers(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})).generate(world, rand, j15, j17, j20);
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
        }
        
        RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
