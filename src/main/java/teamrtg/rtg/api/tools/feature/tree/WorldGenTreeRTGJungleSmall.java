package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static java.lang.Math.*;
import static net.minecraft.init.Blocks.DIRT;
import static net.minecraft.init.Blocks.GRASS;

public class WorldGenTreeRTGJungleSmall extends WorldGenerator {
    private Block blockLog;
    private int metadataLog;
    private Block blockLeaves;
    private int metadataLeaves;
    private int base;
    private int root;
    private float length;
    private int branch;
    private float verStart;
    private float verRand;

    public WorldGenTreeRTGJungleSmall(Block LOG, int metaLog, Block LEAVES, int metaLeaves, int baseHeight, int rootHeight, float branchLength, int numBranches, float verticalStart, float verticalRand) {
        blockLog = LOG;
        metadataLog = metaLog;
        blockLeaves = LEAVES;
        metadataLeaves = metaLeaves;

        base = baseHeight;
        root = rootHeight;
        length = branchLength;

        branch = numBranches;
        verStart = verticalStart;
        verRand = verticalRand;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block b = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
        if (b != GRASS && b != DIRT) {
            return false;
        }

        if (root > 0f) {
            for (int k = 0; k < 3; k++) {
                generateBranch(world, rand, x, y + root, z, (120 * k) - 40 + rand.nextInt(80), 1.6f + rand.nextFloat() * 0.1f, root * 1.7f, 1f);
            }
        }

        for (int i = y + root; i < y + base; i++) {
            world.setBlockState(new BlockPos(x, i, z), blockLog.getStateFromMeta(metadataLog), 2);
        }

        float horDir, verDir;
        int eX, eY, eZ;
        for (int j = 0; j < branch; j++) {
            horDir = (120 * j) - 60 + rand.nextInt(120);
            verDir = verStart + rand.nextFloat() * verRand;
            generateBranch(world, rand, x, y + base, z, horDir, verDir, length, 1f);

            eX = x + (int) (cos(horDir * PI / 180D) * verDir * length);
            eZ = z + (int) (sin(horDir * PI / 180D) * verDir * length);
            eY = y + base + (int) ((1f - verDir) * length);

            for (int m = 0; m < 1; m++) {
                generateLeaves(world, rand, eX, eY, eZ, 4f, 1.5f);
            }
        }

        return true;
    }

    /*
             * horDir = number between -180D and 180D
             * verDir = number between 1F (horizontal) and 0F (vertical)
             */
    public void generateBranch(World world, Random rand, float x, float y, float z, double horDir, float verDir, float length, float speed) {
        if (verDir < 0f) {
            verDir = -verDir;
        }

        float c = 0f;
        float velY = 1f - verDir;

        if (verDir > 1f) {
            verDir = 1f - (verDir - 1f);
        }

        float velX = (float) cos(horDir * PI / 180D) * verDir;
        float velZ = (float) sin(horDir * PI / 180D) * verDir;

        while (c < length) {
            world.setBlockState(new BlockPos((int) x, (int) y, (int) z), blockLog.getStateFromMeta(metadataLog), 2);

            x += velX;
            y += velY;
            z += velZ;

            c += 1f;
        }
    }

    public void generateLeaves(World world, Random rand, int x, int y, int z, float size, float width) {
        float dist;
        int i, j, k, s = (int) (4f - 1f), w = (int) ((4f - 1f) * 1.5f);
        for (i = -w; i <= w; i++) {
            for (j = -s; j <= s; j++) {
                for (k = -w; k <= w; k++) {
                    dist = abs((float) i / 1.5f) + (float) abs(j) + abs((float) k / 1.5f);
                    if (dist <= 4f - 0.5f || (dist <= 4f && rand.nextBoolean())) {
                        if (dist < 0.6f) {
                            world.setBlockState(new BlockPos(x + i, y + j, z + k), blockLog.getStateFromMeta(metadataLog), 2);
                        }
                        if (world.isAirBlock(new BlockPos(x + i, y + j, z + k))) {
                            world.setBlockState(new BlockPos(x + i, y + j, z + k), blockLeaves.getStateFromMeta(metadataLeaves), 2);
                        }
                    }
                }
            }
        }
    }
}
