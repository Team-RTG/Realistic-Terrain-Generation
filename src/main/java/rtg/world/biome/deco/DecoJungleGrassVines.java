package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenVinesRTG;

/**
 * @author WhichOnesPink
 */
public class DecoJungleGrassVines extends DecoBase {

    protected WorldGenerator worldgeneratorGrass;
    protected WorldGenerator worldgeneratorDoubleTallgrass;
    protected WorldGenerator worldgeneratorFern;
    protected WorldGenerator worldgeneratorLargeFern;
    protected WorldGenerator worldgeneratorVines;

    public DecoJungleGrassVines() {

        super();

        this.worldgeneratorGrass = new WorldGenGrass(Blocks.TALLGRASS.getStateFromMeta(1), 1);
        this.worldgeneratorDoubleTallgrass = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(2), 2);
        this.worldgeneratorFern = new WorldGenGrass(Blocks.TALLGRASS.getStateFromMeta(2), 2);
        this.worldgeneratorLargeFern = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(3), 3);
        this.worldgeneratorVines = new WorldGenVinesRTG();

        this.addDecoTypes(DecoType.GRASS, DecoType.VINE);
    }

    /**
     * No config options for this one yet. Just ripped it directly from the old code.
     */
    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int worldX, int worldY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(world, rand, new BlockPos(worldX, 0, worldY), GRASS)) {

                for (int l14 = 0; l14 < 16f * strength; l14++) {
                    int l19 = worldX + rand.nextInt(16);// + 8;
                    int k22 = rand.nextInt(128);
                    int j24 = worldY + rand.nextInt(16);// + 8;

                    if (rand.nextInt(8) == 0) {
                        if (rand.nextBoolean()) {
                            this.worldgeneratorGrass.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                        else {
                            this.worldgeneratorFern.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                    }

                    for (int h44 = 0; h44 < 4 && k22 > 63; h44++) {
                        worldgeneratorVines.generate(world, rand, new BlockPos(l19, k22, j24));
                    }
                }

                for (int l14 = 0; l14 < 12f * strength; l14++) {
                    int l19 = worldX + rand.nextInt(16);// + 8;
                    int k22 = rand.nextInt(128);
                    int j24 = worldY + rand.nextInt(16);// + 8;

                    if (rand.nextInt(5) == 0) {
                        if (rand.nextBoolean()) {
                            this.worldgeneratorDoubleTallgrass.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                        else {
                            this.worldgeneratorLargeFern.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                    }
                }

                for (int l14 = 0; l14 < 16f * strength; l14++) {
                    int l19 = worldX + rand.nextInt(16);// + 8;
                    int k22 = rand.nextInt(128);
                    int j24 = worldY + rand.nextInt(16);// + 8;

                    if (rand.nextInt(8) == 0) {
                        if (rand.nextBoolean()) {
                            this.worldgeneratorGrass.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                        else {
                            this.worldgeneratorFern.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                    }

                    if (k22 > 63) {

                        for (int h44 = 0; h44 < 8; h44++) {
                            worldgeneratorVines.generate(world, rand, new BlockPos(l19, k22, j24));
                        }
                    }
                }
            }
        }
    }
}
