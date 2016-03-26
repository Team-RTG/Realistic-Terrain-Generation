package rtg.world.gen.feature;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.init.Blocks.*;

public class WorldGenFlowersRTG extends WorldGenerator {
    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    private int[] flowers;

    /**
     * FLOWER LIST:
     * <p/>
     * 0	Rose -
     * 1	Blue Orchid -
     * 2	Allium -
     * 3	Azure Bluet -
     * 4	Red Tulip -
     * 5	Orange Tulip -
     * 6	White Tulip -
     * 7	Pink Tulip -
     * 8	Oxeye Daisy -
     * <p/>
     * 9	yellow Flower -
     * <p/>
     * 10	Sunflower -
     * 11	Lilac -
     * 12	Double Tallgrass -
     * 13	Large Fern -
     * 14	Rose Bush -
     * 15	Peony
     */
    public WorldGenFlowersRTG(int[] f)
    {
    	flowers = f;
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int randomFlower = flowers[rand.nextInt(flowers.length)];

        if (randomFlower > 9) {
            for (int l = 0; l < 64; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && (!world.provider.getHasNoSky() || j1 < 254) && double_plant.canPlaceBlockAt(world, new BlockPos(i1, j1, k1))) {
                    world.setBlockState(new BlockPos(i1, j1, k1), Blocks.double_plant.getStateFromMeta(randomFlower - 10), 0);
                }
            }
        } else if (randomFlower == 9) {
            for (int l = 0; l < 64; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(new BlockPos(i1, j1, k1))) && (!world.provider.getHasNoSky() || j1 < 255)
                        && yellow_flower.canBlockStay(world, new BlockPos(i1, j1, k1), yellow_flower.getDefaultState())) {
                    world.setBlockState(new BlockPos(i1, j1, k1), yellow_flower.getDefaultState(), 0);
                }
            }
        } else {
            for (int l = 0; l < 64; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && (!world.provider.getHasNoSky() || j1 < 255) &&
                        red_flower.canPlaceBlockAt(world, new BlockPos(i1, j1, k1))) {
                    world.setBlockState(new BlockPos(i1, j1, k1), red_flower.getStateFromMeta(randomFlower), 0);
                }
            }
        }

        return true;
    }
}