package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.*;
import static net.minecraft.init.Blocks.*;

public class WorldGenTreeRTGPineEuro extends WorldGenerator {
    public WorldGenTreeRTGPineEuro() {
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block g = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
        if (g != GRASS && g != DIRT) {
            return false;
        }

        int height = 18 + rand.nextInt(10);
        int leafheight = 7 + rand.nextInt(4);
        float branchIncrease = 0.25f;

        for (int i = 0; i <= height; i++) {
            world.setBlockState(new BlockPos(x, y + i, z), LOG.getDefaultState(), 0);
        }
        createRandomLeaves(world, rand, x, y + height, z, 2);
        createTrunk(world, rand, x, y, z);

        int dir = 0, b;
        float xd, yd, bl = 1f;
        for (int j = height; j >= height - leafheight; j--) {
            bl += branchIncrease;
            dir += rand.nextInt(180) + 90;
            dir -= dir > 360 ? 360 : 0;
            xd = (float) cos(dir * PI / 180f);
            yd = (float) sin(dir * PI / 180f);

            for (b = 0; b <= bl; b++) {
                world.setBlockState(new BlockPos(x + (int) (b * xd), y + j, z + (int) (b * yd)), LOG.getStateFromMeta(12), 0);
            }
            createRandomLeaves(world, rand, x, y + j, z, 2);
            createRandomLeaves(world, rand, x + (int) (b * xd), y + j, z + (int) (b * yd), 2);
        }

        return true;
    }

    private void createRandomLeaves(World world, Random rand, int x, int y, int z, int size) {
        int l;
        int t = (int) pow(2, 2);
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    l = i * i + j * j + k * k;
                    if (l <= t) {
                        if (world.isAirBlock(new BlockPos(x + i, y + j, z + k)) && (l < t / 2 || rand.nextBoolean())) {
                            world.setBlockState(new BlockPos(x + i, y + j, z + k), LEAVES.getStateFromMeta(4), 0);
                        }
                    }
                }
            }
        }
    }

    private void createTrunk(World world, Random rand, int x, int y, int z) {
        int[] pos = new int[] {0, 0, 1, 0, 0, 1, -1, 0, 0, -1};
        int sh;
        for (int t = 0; t < 5; t++) {
            sh = rand.nextInt(3) + y;
            while (sh > y - 3) {
                if (world.getBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1])).getBlock() == DIRT) {
                    break;
                }
                world.setBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1]), LOG.getStateFromMeta(12), 0);
                sh--;
            }
        }
    }
}
