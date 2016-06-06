package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.abs;
import static net.minecraft.init.Blocks.*;

public class WorldGenTreeRTGSpruceSmall extends WorldGenerator {
    private int treeSize;

    public WorldGenTreeRTGSpruceSmall(int s) {
        treeSize = s;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block g = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
        if (g != GRASS && g != DIRT) {
            return false;
        }

        int leavesMeta = rand.nextInt(2);

        int start = 1;
        int small = 2;
        int large = 0;
        if (treeSize == 1) {
            small = 2;
            large = 2;
        } else if (treeSize == 2) {
            start = 1 + rand.nextInt(2);
            small = 3;
            large = 3;
        }

        int i, j, k;
        for (i = 0; i < start; i++) {
            world.setBlockState(new BlockPos(x, y, z), LOG.getDefaultState(), 0);
            y++;
        }

        for (i = 0; i < large; i++) {
            for (j = -2; j <= 2; j++) {
                for (k = -2; k <= 2; k++) {
                    if (abs(j) + abs(k) != 4 && ((j > -2 && k > -2 && j < 2 && k < 2) || rand.nextInt(4) != 0)) {
                        world.setBlockState(new BlockPos(x + j, y, z + k), LEAVES.getStateFromMeta(leavesMeta), 0);
                    }
                }
            }
            world.setBlockState(new BlockPos(x, y, z), LOG.getDefaultState(), 0);
            y++;
        }

        for (i = 0; i < small; i++) {
            for (j = -1; j <= 1; j++) {
                for (k = -1; k <= 1; k++) {
                    if (abs(j) + abs(k) < 2 || (rand.nextInt(4) != 0)) {
                        world.setBlockState(new BlockPos(x + j, y, z + k), LEAVES.getStateFromMeta(leavesMeta), 0);
                    }
                }
            }

            if (i == 0) {
                world.setBlockState(new BlockPos(x + 1, y, z), LEAVES.getStateFromMeta(leavesMeta), 0);
                world.setBlockState(new BlockPos(x - 1, y, z), LEAVES.getStateFromMeta(leavesMeta), 0);
                world.setBlockState(new BlockPos(x, y, z + 1), LEAVES.getStateFromMeta(leavesMeta), 0);
                world.setBlockState(new BlockPos(x, y, z - 1), LEAVES.getStateFromMeta(leavesMeta), 0);
                world.setBlockState(new BlockPos(x + 2, y, z), LEAVES.getStateFromMeta(leavesMeta), 0);
                world.setBlockState(new BlockPos(x - 2, y, z), LEAVES.getStateFromMeta(leavesMeta), 0);
                world.setBlockState(new BlockPos(x, y, z + 2), LEAVES.getStateFromMeta(leavesMeta), 0);
                world.setBlockState(new BlockPos(x, y, z - 2), LEAVES.getStateFromMeta(leavesMeta), 0);
            }

            world.setBlockState(new BlockPos(x, y, z), LOG.getDefaultState(), 0);
            y++;
        }

        world.setBlockState(new BlockPos(x, y, z), LOG.getDefaultState(), 0);
        world.setBlockState(new BlockPos(x + 1, y, z), LEAVES.getStateFromMeta(leavesMeta), 0);
        world.setBlockState(new BlockPos(x - 1, y, z), LEAVES.getStateFromMeta(leavesMeta), 0);
        world.setBlockState(new BlockPos(x, y, z + 1), LEAVES.getStateFromMeta(leavesMeta), 0);
        world.setBlockState(new BlockPos(x, y, z - 1), LEAVES.getStateFromMeta(leavesMeta), 0);

        world.setBlockState(new BlockPos(x, y + 1, z), LEAVES.getStateFromMeta(leavesMeta), 0);
        world.setBlockState(new BlockPos(x, y + 2, z), LEAVES.getStateFromMeta(leavesMeta), 0);
        return true;
    }
}
