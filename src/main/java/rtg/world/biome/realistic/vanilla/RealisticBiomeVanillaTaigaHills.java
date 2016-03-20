package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaHills;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSpruceSmall;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaHills;

import java.util.Random;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.taigaHills.topBlock;
    public static IBlockState fillerBlock = Biomes.taigaHills.fillerBlock;

    public RealisticBiomeVanillaTaigaHills(BiomeConfig config) {

        super(config,
                Biomes.taigaHills,
                Biomes.river,
                new TerrainVanillaTaigaHills(),
                new SurfaceVanillaTaigaHills(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), true, Blocks.gravel.getDefaultState(), 0.2f));
        this.noLakes = true;
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        // boulders
        for (int l = 0; l < 3f * strength; ++l) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.mossy_cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        // trees
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        for (int b1 = 0; b1 < l * 4f * strength; b1++) {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

            WorldGenerator worldgenerator =
                    rand.nextInt(4) == 0 ? new WorldGenTreeRTGSpruceSmall(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? new WorldGenTreeRTGPineSmall(
                            1 + rand.nextInt(3), 4 + rand.nextInt(4)) : new WorldGenTreeRTGPineSmall(4 + rand.nextInt(6), 5 + rand.nextInt(10));
            worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
        }

        if (this.config.getPropertyById(BiomeConfigVanillaTaigaHills.decorationLogsId).valueBoolean) {

            if (l > 0f && rand.nextInt(6) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }

        for (int b = 0; b < 2f * strength; b++) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            if (rand.nextInt(10) == 0) {
                (new WorldGenTreeRTGShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, new BlockPos(i1, k1, j1));
            } else {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        if (rand.nextInt((int) (3f / strength)) == 0) {
            int k15 = chunkX + rand.nextInt(16) + 8;
            int k17 = rand.nextInt(64) + 64;
            int k20 = chunkY + rand.nextInt(16) + 8;

            if (rand.nextBoolean()) {
                (new WorldGenBush(Blocks.brown_mushroom)).generate(world, rand, new BlockPos(k15, k17, k20));
            } else {
                (new WorldGenBush(Blocks.red_mushroom)).generate(world, rand, new BlockPos(k15, k17, k20));
            }
        }

        if (rand.nextInt((int) (20f / strength)) == 0) {
            int j16 = chunkX + rand.nextInt(16) + 8;
            int j18 = rand.nextInt(128);
            int j21 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
        }

        for (int l14 = 0; l14 < 10f * strength; l14++) {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
        }
    }
}
