package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.module.Mods;

import java.util.Random;

import static java.lang.Math.abs;
import static net.minecraft.init.Blocks.*;


public class WorldGenTreeRTGRedwood extends WorldGenerator {
    private int height;
    private int LEAVES;
    private int trunk;
    private int metadata;

    public WorldGenTreeRTGRedwood(int h, int l, int t, int m) {
        height = h;
        LEAVES = l;
        trunk = t;
        metadata = m;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block g = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

        if (g == SAND && !Mods.RTG.config.ALLOW_TREES_ON_SAND.get()) {
            return false;
        }

        if (g != GRASS && g != DIRT && g != SAND) {
            return false;
        }

        for (int l1 = 0; l1 < 5; l1++) {
            genLeaves(world, rand, x - 1 + rand.nextInt(3), y + height - l1, z - 1 + rand.nextInt(3), 1);
            genLeaves(world, rand, x - 1 + rand.nextInt(3), y + height - l1, z - 1 + rand.nextInt(3), 1);
        }
        for (int l2 = 5; l2 < LEAVES; l2++) {
            genLeaves(world, rand, x - 2 + rand.nextInt(5), y + height - l2, z - 2 + rand.nextInt(5), 2);
            if (rand.nextBoolean()) {
                genLeaves(world, rand, x - 2 + rand.nextInt(5), y + height - l2, z - 2 + rand.nextInt(5), 2);
            }
        }

        for (int i = 0; i < height; i++) {
            world.setBlockState(new BlockPos(x, y + i, z), LOG.getDefaultState(), 0);
        }
        world.setBlockState(new BlockPos(x, y + height, z), Blocks.LEAVES.getStateFromMeta(metadata), 0);
        createTrunk(world, rand, x, y, z);

        return true;
    }

    public void genLeaves(World world, Random rand, int x, int y, int z, int size) {
        int i;
        int j;
        int dis;
        for (i = -1; i <= 1; i++) {
            for (j = -1; j <= 1; j++) {
                dis = abs(i) + abs(j);
                if (world.isAirBlock(new BlockPos(x + i, y + 1, z + j)) && (dis < size - 1 || (dis < size && rand.nextBoolean()))) {
                    world.setBlockState(new BlockPos(x + i, y + 1, z + j), Blocks.LEAVES.getStateFromMeta(metadata), 0);
                }
            }
        }

        for (i = -2; i <= 2; i++) {
            for (j = -2; j <= 2; j++) {
                dis = abs(i) + abs(j);
                if (world.isAirBlock(new BlockPos(x + i, y, z + j)) && (dis < size * 2 - 1 || (dis < size * 2 && rand.nextBoolean()))) {
                    world.setBlockState(new BlockPos(x + i, y, z + j), Blocks.LEAVES.getStateFromMeta(metadata), 0);
                }
            }
        }

        if (size > 1) {
            world.setBlockState(new BlockPos(x, y, z), LOG.getStateFromMeta(12), 0);
        }
    }

    private void createTrunk(World world, Random rand, int x, int y, int z) {
        int[] pos = new int[] {0, 0, 1, 0, 0, 1, -1, 0, 0, -1, 1, 1, 1, -1, -1, 1, -1, -1};
        int sh;
        Block b;
        for (int t = 0; t < 9; t++) {
            sh = pos[t * 2] == 0 || pos[t * 2 + 1] == 0 ? rand.nextInt(trunk * 2) + y + trunk : rand.nextInt(trunk) + y - 1;
            while (sh > y - 2) {
                if (world.getBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1])).getBlock() == GRASS) {
                    break;
                }
                world.setBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1]), LOG.getStateFromMeta(12), 0);
                sh--;
            }
        }
    }
}
