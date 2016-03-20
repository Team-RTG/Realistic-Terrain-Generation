package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPRedwoodForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRedwoodForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPRedwoodForest;

import java.util.Random;

public class RealisticBiomeBOPRedwoodForest extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.redwood_forest.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPRedwoodForest(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPRedwoodForest(58f, 80f, 30f),
                new SurfaceBOPRedwoodForest(config, topBlock, fillerBlock, false, null, 0.4f)
        );
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        for (int i23 = 0; i23 < 1; i23++) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

            if (rand.nextInt(16) == 0) {

                if (rand.nextBoolean()) {

                    (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
                } else {

                    (new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }

        if (this.config.getPropertyById(BiomeConfigBOPRedwoodForest.decorationLogsId).valueBoolean) {

            if (rand.nextInt(12) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();

                Block log;
                byte logMeta;
                int intLogLength;

                int intLogRand = rand.nextInt(12);

                if (intLogRand < 3) {

                    log = BOPBlocks.log_3;
                    logMeta = (byte) 0;
                    intLogLength = 3 + rand.nextInt(6);
                } else if (intLogRand < 9) {

                    log = BOPBlocks.log_3;
                    logMeta = (byte) 0;
                    intLogLength = 3 + rand.nextInt(8);
                } else {

                    log = BOPBlocks.log_3;
                    logMeta = (byte) 0;
                    intLogLength = 3 + rand.nextInt(10);
                }

                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
    }
}
