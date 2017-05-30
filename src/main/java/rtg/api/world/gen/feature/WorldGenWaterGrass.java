package rtg.api.world.gen.feature;


import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.BlockUtil;

public class WorldGenWaterGrass extends WorldGenerator {

    private Block blockData;
    private int varMetadata;
    private int varMinHeight;

    public WorldGenWaterGrass(Block block, int metadata, int minHeight) {

        blockData = block;
        varMetadata = metadata;
        varMinHeight = minHeight;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {

        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {

        while (y > 0) {
            if (!world.isAirBlock(new BlockPos(x, y, z)) || world.getBlockState(new BlockPos(x, y, z)).getBlock().isLeaves(world.getBlockState(new BlockPos(x, y, z)), world, new BlockPos(x, y, z))) {
                break;
            }

            if (y < varMinHeight) {
                return false;
            }
            y--;
        }

        Block b;
        if (blockData == Blocks.DOUBLE_PLANT) {
            int i1, j1, k1;
            for (int l = 0; l < 32; ++l) {
                i1 = x + rand.nextInt(8) - rand.nextInt(8);
                j1 = y + rand.nextInt(2) - rand.nextInt(2);
                k1 = z + rand.nextInt(8) - rand.nextInt(8);

                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
                if (((b == Blocks.WATER && world.getBlockState(new BlockPos(i1, j1 - 2, k1)).getBlock() == Blocks.SAND) || b == Blocks.SAND) && world.getBlockState(new BlockPos(i1, j1, k1)).getBlock() == Blocks.AIR) {
                    world.setBlockState(new BlockPos(i1, j1 - 1, k1), Blocks.GRASS.getDefaultState(), 0);
                }

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && j1 < 254 && Blocks.DOUBLE_PLANT.canPlaceBlockAt(world, new BlockPos(i1, j1, k1))) {
                    world.setBlockState(new BlockPos(i1, j1, k1), Blocks.DOUBLE_PLANT.getStateFromMeta(varMetadata));
                }
            }
        }
        else if (blockData == Blocks.LEAVES) {
            for (int l = 0; l < 64; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
                if (((b == Blocks.WATER && world.getBlockState(new BlockPos(i1, j1 - 2, k1)).getBlock() == Blocks.SAND) || b == Blocks.SAND) && world.getBlockState(new BlockPos(i1, j1, k1)).getBlock() == Blocks.AIR) {
                    world.setBlockState(new BlockPos(i1, j1 - 1, k1), Blocks.GRASS.getDefaultState(), 0);
                }

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock() == Blocks.GRASS) {
                    world.setBlockState(new BlockPos(i1, j1, k1), BlockUtil.getStateLeaf(varMetadata), 0);
                }
            }
        }
        else {
            for (int l = 0; l < 128; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
                if (((b == Blocks.WATER && world.getBlockState(new BlockPos(i1, j1 - 2, k1)).getBlock() == Blocks.SAND) || b == Blocks.SAND) && world.getBlockState(new BlockPos(i1, j1, k1)).getBlock() == Blocks.AIR) {
                    world.setBlockState(new BlockPos(i1, j1 - 1, k1), Blocks.GRASS.getDefaultState(), 0);
                }

                if (world.isAirBlock(new BlockPos(i1, j1, k1))
                    //TODO replace this
                    // && block.canBlockStay(world, new BlockPos(i1, j1, k1))
                    ) {
                    world.setBlockState(new BlockPos(i1, j1, k1), blockData.getStateFromMeta(varMetadata), 0);
                }
            }
        }
        return true;
    }
}
