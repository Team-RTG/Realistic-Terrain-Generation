package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.abs;
import static net.minecraft.block.material.Material.AIR;
import static net.minecraft.init.Blocks.*;

public class WorldGenTreeRTGBirch extends WorldGenerator {
    private int startHeight;
    private int treeSize;

    public WorldGenTreeRTGBirch(int start, int s) {
        startHeight = start;
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

        int i;
        for (i = 0; i < startHeight; i++) {
            world.setBlockState(new BlockPos(x, y, z), LOG.getStateFromMeta(2), 0);
            y++;
        }

        int pX = 0;
        int pZ = 0;
        for (i = 0; i < treeSize; i++) {
            if (rand.nextInt(2) == 0 && i < treeSize - 2) {
                int dX = -1 + rand.nextInt(3);
                int dZ = -1 + rand.nextInt(3);

                if (dX == 0 && dZ == 0) {
                    dX = -1 + rand.nextInt(3);
                    dZ = -1 + rand.nextInt(3);
                }

                if (pX == dX && rand.nextBoolean()) {
                    dX = -dX;
                }
                if (pZ == dZ && rand.nextBoolean()) {
                    dZ = -dZ;
                }

                pX = dX;
                pZ = dZ;

                buildBranch(world, rand, x, y, z, dX, dZ, 1, i < treeSize - 2 ? 2 : 1); //i < treeSize - 4 ? 2 : 1
            }
            world.setBlockState(new BlockPos(x, y, z), LOG.getStateFromMeta(2), 0);

            if (i < treeSize - 2) {
                if (rand.nextBoolean()) {
                    buildLeaves(world, x, y, z + 1);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x, y, z - 1);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x + 1, y, z);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x - 1, y, z);
                }
            }

            y++;
        }

        buildLeaves(world, x, y - 1, z + 1);
        buildLeaves(world, x, y - 1, z - 1);
        buildLeaves(world, x + 1, y - 1, z);
        buildLeaves(world, x - 1, y - 1, z);
        buildLeaves(world, x, y, z);

        return true;
    }

    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = 0; k < 2; k++) {
                    if (abs(i) + abs(j) + abs(k) < leaveSize + 1) {
                        buildLeaves(world, x + i + (dX * 1), y + k, z + j + (dZ * 1));
                    }
                }
            }
        }

        for (int m = 1; m <= 1; m++) {
            world.setBlockState(new BlockPos(x + (dX * m), y, z + (dZ * m)), LOG.getStateFromMeta(2), 0);
        }
    }

    public void buildLeaves(World world, int x, int y, int z) {
        Block b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (b.getMaterial(b.getDefaultState()) == AIR) {
            world.setBlockState(new BlockPos(x, y, z), LEAVES.getStateFromMeta(2), 0);
        }
    }
}
