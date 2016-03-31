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
import rtg.api.config.biomesoplenty.config.BiomeConfigBOPOminousWoods;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOminousWoods;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOminousWoods;

import java.util.Random;

public class RealisticBiomeBOPOminousWoods extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.ominous_woods.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPOminousWoods() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPOminousWoods(65f, 80f, 48f),
                new SurfaceBOPOminousWoods(config, topBlock, fillerBlock)
        );
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the config? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        if (this.config.getPropertyById(BiomeConfigBOPOminousWoods.decorationLogsId).valueBoolean) {

            if (rand.nextInt(6) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();

                Block log;
                byte logMeta;
                int intLogLength;

                int intLogRand = rand.nextInt(12);

                if (intLogRand < 3) {

                    log = BOPBlocks.log_1;
                    logMeta = (byte) 2;
                    intLogLength = 3 + rand.nextInt(4);
                } else {

                    log = BOPBlocks.log_3;
                    logMeta = (byte) 2;
                    intLogLength = 3 + rand.nextInt(2);
                }

                (new WorldGenLog(log, logMeta, Blocks.leaves, -1, intLogLength)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
    }
}
