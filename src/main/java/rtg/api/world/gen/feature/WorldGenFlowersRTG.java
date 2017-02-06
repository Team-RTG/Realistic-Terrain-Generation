package rtg.api.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil;

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

                IBlockState doublePlant = BlockUtil.getStateFlower(randomFlower);
                BlockPos doublePlantPos = new BlockPos(i1, j1, k1);
                WorldUtil worldUtil = new WorldUtil(world);

                if (world.isAirBlock(doublePlantPos)
                    && (!world.provider.getHasNoSky() || j1 < 254)
                    && Blocks.DOUBLE_PLANT.canPlaceBlockAt(world, doublePlantPos)) {

                    worldUtil.setDoublePlant(doublePlantPos, doublePlant);
                }
            }
        }
        else if (randomFlower == 9) {
            //for (int l = 0; l < 64; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y;// + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                IBlockState flower = BlockUtil.getStateFlower(randomFlower);
                BlockPos flowerPos = new BlockPos(i1, j1, k1);

                if (world.isAirBlock(flowerPos)
                    && (!world.provider.getHasNoSky() || j1 < 254)
                    && Blocks.YELLOW_FLOWER.canPlaceBlockAt(world, flowerPos)
                    && Blocks.YELLOW_FLOWER.canBlockStay(world, flowerPos, flower)) {

                    world.setBlockState(flowerPos, flower, 2);
                }
            }
        }
        else {
            //for (int l = 0; l < 64; ++l)
            {
                int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y;// + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

                IBlockState flower = BlockUtil.getStateFlower(randomFlower);
                BlockPos flowerPos = new BlockPos(i1, j1, k1);

                if (world.isAirBlock(flowerPos)
                    && (!world.provider.getHasNoSky() || j1 < 254)
                    && Blocks.RED_FLOWER.canPlaceBlockAt(world, flowerPos)
                    && Blocks.RED_FLOWER.canBlockStay(world, flowerPos, flower)) {

                    world.setBlockState(flowerPos, flower, 2);
                }
            }
        }

        return true;
    }
}