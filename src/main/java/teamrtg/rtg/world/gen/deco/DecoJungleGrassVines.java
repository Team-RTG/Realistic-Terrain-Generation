package teamrtg.rtg.world.gen.deco;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.util.math.RandomUtil;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.gen.feature.WorldGenGrass;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

/**
 * @author WhichOnesPink
 */
public class DecoJungleGrassVines extends DecoBase {

    public DecoJungleGrassVines() {
        super();

        this.addDecoTypes(DecoType.GRASS, DecoType.VINE);
    }

    /**
     * No config options for this one yet. Just ripped it directly from the old code.
     */
    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
        if (this.allowed) {

            if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), GRASS)) {

                for (int l14 = 0; l14 < 16f * strength; l14++) {
                    int l19 = chunkX + rand.nextInt(16) + 8;
                    int k22 = rand.nextInt(128);
                    int j24 = chunkY + rand.nextInt(16) + 8;
                    int grassMeta;

                    if (rand.nextInt(8) == 0) {
                        grassMeta = 0;
                    } else {
                        grassMeta = RandomUtil.getRandomInt(rand, 1, 2);
                    }

                    (new WorldGenGrass(Blocks.tallgrass, grassMeta)).generate(world, rand, new BlockPos(l19, k22, j24));

                    for (int h44 = 0; h44 < 4 && k22 > 63; h44++) {
                        WorldGenerator worldgenerator4 = new WorldGenVines();
                        worldgenerator4.generate(world, rand, new BlockPos(l19, k22, j24));
                    }
                }

                for (int l14 = 0; l14 < 12f * strength; l14++) {
                    int l19 = chunkX + rand.nextInt(16) + 8;
                    int k22 = rand.nextInt(128);
                    int j24 = chunkY + rand.nextInt(16) + 8;

                    if (rand.nextInt(5) == 0) {
                        (new WorldGenGrass(Blocks.double_plant, RandomUtil.getRandomInt(rand, 2, 3))).generate(world, rand, new BlockPos(l19, k22, j24));
                    }
                }

                for (int l14 = 0; l14 < 16f * strength; l14++) {
                    int l19 = chunkX + rand.nextInt(16) + 8;
                    int k22 = rand.nextInt(128);
                    int j24 = chunkY + rand.nextInt(16) + 8;
                    int grassMeta;

                    if (rand.nextInt(8) == 0) {
                        grassMeta = 0;
                    } else {
                        grassMeta = RandomUtil.getRandomInt(rand, 1, 2);
                    }

                    (new WorldGenGrass(Blocks.tallgrass, grassMeta)).generate(world, rand, new BlockPos(l19, k22, j24));

                    if (k22 > 63) {

                        for (int h44 = 0; h44 < 8; h44++) {
                            WorldGenerator worldgenerator4 = new WorldGenVines();
                            worldgenerator4.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                    }
                }
            }
        }
    }
}
