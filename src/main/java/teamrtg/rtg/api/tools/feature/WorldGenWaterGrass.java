package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.init.Blocks.*;

public class WorldGenWaterGrass extends WorldGenerator {
    private Block block;
    private int metadata;
    private int minHeight;

    public WorldGenWaterGrass(Block b, int m) {
        this(b, m, 10);
    }

    public WorldGenWaterGrass(Block b, int m, int mh) {
        block = b;
        metadata = m;
        minHeight = 10;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        while (y > 0) {
            if (!world.isAirBlock(new BlockPos(x, y, z)) || world.getBlockState(new BlockPos(x, y, z)).getBlock().isLeaves(world.getBlockState(new BlockPos(x, y, z)), world, new BlockPos(x, y, z))) {
                break;
            }

            if (y < minHeight) {
                return false;
            }
            y--;
        }

        Block b;
        if (block == DOUBLE_PLANT) {
            int i1, j1, k1;
            for (int l = 0; l < 32; ++l) {
                i1 = x + rand.nextInt(8) - rand.nextInt(8);
                j1 = y + rand.nextInt(2) - rand.nextInt(2);
                k1 = z + rand.nextInt(8) - rand.nextInt(8);

                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
                if (((b == WATER && world.getBlockState(new BlockPos(i1, j1 - 2, k1)).getBlock() == SAND) || b == SAND) && world.getBlockState(new BlockPos(i1, j1, k1)).getBlock() == AIR) {
                    world.setBlockState(new BlockPos(i1, j1 - 1, k1), GRASS.getDefaultState(), 0);
                }

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && j1 < 254 && DOUBLE_PLANT.canPlaceBlockAt(world, new BlockPos(i1, j1, k1))) {
                    world.setBlockState(new BlockPos(i1, j1, k1), Blocks.DOUBLE_PLANT.getStateFromMeta(metadata));
                }
            }
        } else if (block == LEAVES) {
            for (int l = 0; l < 64; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
                if (((b == WATER && world.getBlockState(new BlockPos(i1, j1 - 2, k1)).getBlock() == SAND) || b == SAND) && world.getBlockState(new BlockPos(i1, j1, k1)).getBlock() == AIR) {
                    world.setBlockState(new BlockPos(i1, j1 - 1, k1), GRASS.getDefaultState(), 0);
                }

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock() == GRASS) {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(metadata), 0);
                }
            }
        } else {
            for (int l = 0; l < 128; ++l) {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
                if (((b == WATER && world.getBlockState(new BlockPos(i1, j1 - 2, k1)).getBlock() == SAND) || b == SAND) && world.getBlockState(new BlockPos(i1, j1, k1)).getBlock() == AIR) {
                    world.setBlockState(new BlockPos(i1, j1 - 1, k1), GRASS.getDefaultState(), 0);
                }

                if (world.isAirBlock(new BlockPos(i1, j1, k1))
                    //TODO replace this
                    // && block.canBlockStay(world, new BlockPos(i1, j1, k1))
                        ) {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(metadata), 0);
                }
            }
        }
        return true;
    }
}
