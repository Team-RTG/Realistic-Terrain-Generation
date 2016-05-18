package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.*;
import static net.minecraft.init.Blocks.*;

public class WorldGenTreeRTGPalmCustom extends WorldGenerator {
    private static int leavesLength = 133;
    private static int[] LEAVES = new int[] {
            1, 0, 0,
            2, 0, 0,
            3, -1, 0,
            3, -2, 0,
            -1, 0, 0,
            -2, 0, 0,
            -3, -1, 0,
            -3, -2, 0,
            0, 0, 1,
            0, 0, 2,
            0, -1, 3,
            0, -2, 3,
            0, 0, -1,
            0, 0, -2,
            0, -1, -3,
            0, -2, -3,
            0, 1, 0,
            1, 1, 1,
            -1, 1, -1,
            -1, 1, 1,
            1, 1, -1,
            1, 2, 0,
            -1, 2, 0,
            0, 2, 1,
            0, 2, -1,
            2, 3, 0,
            3, 3, 0,
            4, 2, 0,
            -2, 3, 0,
            -3, 3, 0,
            -4, 2, 0,
            0, 3, 2,
            0, 3, 3,
            0, 2, 4,
            0, 3, -2,
            0, 3, -3,
            0, 2, -4,
            2, 2, -2,
            -2, 2, 2,
            -2, 2, -2,
            2, 2, 2,
            3, 2, -3,
            -3, 2, 3,
            -3, 2, -3,
            3, 2, 3
    };
    private static int cocoasLength = 16;
    private static int[] cocoas = new int[] {
            2, 0, -2, 1,
            1, 1, -2, 0,
            0, 0, -2, -1,
            3, -1, -2, 0
    };
    private float treeLength = 15f;

    public WorldGenTreeRTGPalmCustom(float pTreeLength, int pLeavesLength, int[] pLeaves, int pCocoasLength, int[] pCocoas) {
        treeLength = pTreeLength;
        leavesLength = pLeavesLength;
        LEAVES = pLeaves;
        cocoasLength = pCocoasLength;
        cocoas = pCocoas;
    }

    public WorldGenTreeRTGPalmCustom() {

    }

    public WorldGenTreeRTGPalmCustom(float pTreeLength) {
        treeLength = pTreeLength;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block b = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

        if (b != GRASS && b != DIRT && b != SAND) {
            return false;
        }

        double horDir = getRandomDir(rand);
        float verDir = 0.3f + rand.nextFloat() * 0.4f, length = treeLength, posX = (float) x, posY = (float) y, posZ = (float) z, c = 0f, loss = 0f;

        if (verDir < 0f) {
            verDir = -verDir;
        }

        float velY = 1f - verDir;

        if (verDir > 1f) {
            verDir = 1f - (verDir - 1f);
        }

        float velX = (float) cos(horDir * PI / 180D) * verDir;
        float velZ = (float) sin(horDir * PI / 180D) * verDir;

        while (c < length) {
            world.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ), LOG.getStateFromMeta(15), 2);

            if (c < length - 3) {
                loss = abs(velX) + abs(velZ);
                posX += velX *= 0.9f;
                posZ += velZ *= 0.9f;
                loss = loss - (abs(velX) + abs(velZ));
                posY += velY += loss;
            } else {
                posY += velY;
            }

            c += 1f;
        }

        x = (int) posX;
        y = (int) posY - 1;
        z = (int) posZ;

        for (int j = 0; j < leavesLength; j += 3) {
            world.setBlockState(new BlockPos(x + LEAVES[j], y + LEAVES[j + 1], z + LEAVES[j + 2]), Blocks.LEAVES.getStateFromMeta(3), 2);
        }

        for (int k = 0; k < cocoasLength; k += 4) {
            if (rand.nextInt(20) == 0) {
                world.setBlockState(new BlockPos(x + cocoas[k + 1], y + cocoas[k + 2], z + cocoas[k + 3]), COCOA.getStateFromMeta(cocoas[k + 0] + 8), 2);
            }
        }

        return true;
    }

    public double getRandomDir(Random rand) {
        switch (rand.nextInt(4)) {
            case 0:
                return -180D;
            case 1:
                return -90D;
            case 2:
                return 0D;
            case 3:
                return 90D;
        }
        return 0D;
    }
}
