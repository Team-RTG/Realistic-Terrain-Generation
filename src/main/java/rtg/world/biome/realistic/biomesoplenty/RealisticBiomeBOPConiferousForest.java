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
import rtg.api.config.biomesoplenty.config.BiomeConfigBOPConiferousForest;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPConiferousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPConiferousForest;

import java.util.Random;

public class RealisticBiomeBOPConiferousForest extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.coniferous_forest.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPConiferousForest() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPConiferousForest(58f, 84f, 24f),
                new SurfaceBOPConiferousForest(config,
                        topBlock, //Block top
                        fillerBlock, //Block filler,
                        topBlock, //IBlockState mixTop,
                        fillerBlock, //IBlockState mixFill,
                        80f, //float mixWidth,
                        -0.15f, //float mixHeight,
                        10f, //float smallWidth,
                        0.5f //float smallStrength
                )
        );
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the config? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;

        for (int i23 = 0; i23 < 1; i23++) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

            if (rand.nextInt(16) == 0) {
                (new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        if (this.config.getPropertyById(BiomeConfigBOPConiferousForest.decorationLogsId).valueBoolean) {

            if (l > 0f && rand.nextInt(16) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();

                Block log;
                byte logMeta;

                log = BOPBlocks.log_1;
                logMeta = (byte) 3;

                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, 3 + rand.nextInt(3))).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }

        if (rand.nextInt(12) != 0) {
            rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        } else {
            rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        }
    }
}
