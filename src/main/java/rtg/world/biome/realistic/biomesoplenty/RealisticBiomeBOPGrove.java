package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPGrove;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrubCustom;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGrove;

import java.util.Random;

public class RealisticBiomeBOPGrove extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.grove.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPGrove(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPGrove(),
                new SurfaceBOPGrove(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f)
        );
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        if (this.config.getPropertyById(BiomeConfigBOPGrove.decorationLogsId).valueBoolean) {

            if (rand.nextInt((int) (8f / strength)) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();

                if (y22 < 100) {
                    if (rand.nextBoolean()) {
                        (new WorldGenLog(Blocks.log, (byte) 2, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
                    } else {
                        (new WorldGenLog(Blocks.log2, (byte) 1, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
                    }
                }
            }
        }

        for (int f24 = 0; f24 < 2f * strength; f24++) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

            if (k1 < 110) {
                if (rand.nextBoolean()) {

                    (new WorldGenTreeRTGShrubCustom(rand.nextInt(4) + 1, Blocks.log, (byte) 2, Blocks.leaves, (byte) 2)).generate(world, rand, new BlockPos(i1, k1, j1));
                } else {

                    (new WorldGenTreeRTGShrubCustom(rand.nextInt(4) + 1, Blocks.log2, (byte) 1, Blocks.leaves2, (byte) 1)).generate(world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }

        for (int f23 = 0; f23 < 2f * strength; f23++) {
            int j15 = chunkX + rand.nextInt(16) + 8;
            int j17 = rand.nextInt(128);
            int j20 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenFlowers(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})).generate(world, rand, new BlockPos(j15, j17, j20));
        }

        for (int l14 = 0; l14 < 12f * strength; l14++) {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
        }

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
