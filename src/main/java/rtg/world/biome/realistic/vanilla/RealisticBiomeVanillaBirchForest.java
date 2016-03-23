package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForest;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGBirch;
import rtg.world.gen.feature.tree.WorldGenTreeRTGBirchSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.feature.tree.WorldGenTreeRTGTrees;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForest;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

public class RealisticBiomeVanillaBirchForest extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaBirchForest(BiomeConfig config) {

        super(config,
                Biomes.birchForest,
                Biomes.river,
                new TerrainVanillaBirchForest(),
                new SurfaceVanillaBirchForest(config, Biomes.birchForest.topBlock,
                        Biomes.birchForest.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.15f));
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), TREE)) {

            for (int b1 = 0; b1 < l * strength; b1++) {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

                WorldGenerator worldgenerator = new WorldGenTreeRTGBirchSmall(4 + rand.nextInt(7), 8 + rand.nextInt(12), 2);
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }

            for (int b2 = 0; b2 < 3f * strength; b2++) {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

                if (z52 < 120) {
                    WorldGenerator worldgenerator =
                            rand.nextInt(4) != 0 ? new WorldGenTreeRTGBirch(4 + rand.nextInt(7), 8 + rand.nextInt(12))
                                    : rand.nextInt(10) != 0 ? new WorldGenTreeRTGTrees(false) : new WorldGenTrees(false);
                    worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                }
            }

            if (this.config.getPropertyById(BiomeConfigVanillaBirchForest.decorationLogsId).valueBoolean) {

                if (rand.nextInt((int) (8f / strength)) == 0) {
                    int x22 = chunkX + rand.nextInt(16) + 8;
                    int z22 = chunkY + rand.nextInt(16) + 8;
                    int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                    if (y22 < 100) {
                        (new WorldGenLog(Blocks.log, 2, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
                    }
                }
            }

            for (int f24 = 0; f24 < 3f * strength; f24++) {
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
                if (k1 < 110) {
                    (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }

        if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), GRASS)) {

            for (int l14 = 0; l14 < 12f * strength; l14++) {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
            }
        }

        if (rand.nextInt(3) != 0) {
            rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        } else {
            rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        }
    }
}
