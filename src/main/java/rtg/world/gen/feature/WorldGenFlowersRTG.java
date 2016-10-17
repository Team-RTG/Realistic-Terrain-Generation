package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.util.BlockUtil;

public class WorldGenFlowersRTG extends WorldGenerator {

    private int[] flowers;

    /*
     * FLOWER LIST:
     * 0	Poppy
     * 1	Blue Orchid
     * 2	Allium
     * 3	Azure Bluet
     * 4	Red Tulip
     * 5	Orange Tulip
     * 6	White Tulip
     * 7	Pink Tulip
     * 8	Oxeye Daisy
     * 9	Yellow Flower
     * 10	Sunflower
     * 11	Lilac
     * 12	Double Tallgrass
     * 13	Large Fern
     * 14	Rose Bush
     * 15	Peony
     */
    public WorldGenFlowersRTG(int[] f) {

        flowers = f;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int randomFlower = flowers[rand.nextInt(flowers.length)];

        if (randomFlower > 9) {
            //for (int l = 0; l < 64; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y;// + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                boolean isAirBlock = world.isAirBlock(new BlockPos(i1, j1, k1));
                boolean hasNoSky = world.provider.getHasNoSky();
                boolean canPlaceBlock = Blocks.DOUBLE_PLANT.canPlaceBlockAt(world, new BlockPos(i1, j1, k1));
                boolean canBlockStay = Blocks.DOUBLE_PLANT.canBlockStay(world, new BlockPos(i1, j1, k1), BlockUtil.getStateFlower(randomFlower));

                if (isAirBlock && (!hasNoSky || j1 < 254) && canPlaceBlock && canBlockStay) {
                    world.setBlockState(new BlockPos(i1, j1, k1), BlockUtil.getStateFlower(randomFlower), 2);
                }
            }
        }
        else if (randomFlower == 9) {
            //for (int l = 0; l < 64; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y;// + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                boolean isAirBlock = world.isAirBlock(new BlockPos(i1, j1, k1));
                boolean hasNoSky = world.provider.getHasNoSky();
                boolean canPlaceBlock = Blocks.YELLOW_FLOWER.canPlaceBlockAt(world, new BlockPos(i1, j1, k1));
                boolean canBlockStay = Blocks.YELLOW_FLOWER.canBlockStay(world, new BlockPos(i1, j1, k1), BlockUtil.getStateFlower(randomFlower));

                if (isAirBlock && (!hasNoSky || j1 < 254) && canPlaceBlock && canBlockStay) {
                    world.setBlockState(new BlockPos(i1, j1, k1), BlockUtil.getStateFlower(randomFlower), 2);
                }
            }
        }
        else {
            //for (int l = 0; l < 64; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y;// + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                boolean isAirBlock = world.isAirBlock(new BlockPos(i1, j1, k1));
                boolean hasNoSky = world.provider.getHasNoSky();
                boolean canPlaceBlock = Blocks.RED_FLOWER.canPlaceBlockAt(world, new BlockPos(i1, j1, k1));
                boolean canBlockStay = Blocks.RED_FLOWER.canBlockStay(world, new BlockPos(i1, j1, k1), BlockUtil.getStateFlower(randomFlower));

                if (isAirBlock && (!hasNoSky || j1 < 254) && canPlaceBlock && canBlockStay) {
                    world.setBlockState(new BlockPos(i1, j1, k1), BlockUtil.getStateFlower(randomFlower), 2);
                }
            }
        }

        return true;
    }
}