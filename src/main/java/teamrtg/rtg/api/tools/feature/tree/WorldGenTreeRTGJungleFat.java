package teamrtg.rtg.api.tools.feature.tree;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.module.Mods;

import java.util.Random;

import static java.lang.Math.*;
import static net.minecraft.init.Blocks.*;


public class WorldGenTreeRTGJungleFat extends WorldGenerator {
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

    public WorldGenTreeRTGJungleFat(Block LOG, int metaLog, Block LEAVES, int metaLeaves, int baseHeight, int rootHeight, float branchLength, int numBranches, float verticalStart, float verticalRand) {
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

        if (b == SAND && !Mods.RTG.config.ALLOW_TREES_ON_SAND.get()) {
            return false;
        }

        if (b != GRASS && b != DIRT && b != SAND) {
            return false;
        }

        float r = rand.nextFloat() * 360;
        if (root > 0f) {
            for (int k = 0; k < 5; k++) {
                generateBranch(world, rand, (float) x + 0.5f, y + root, (float) z + 0.5f, (120 * k) - 25 + rand.nextInt(50) + r, 1.6f + rand.nextFloat() * 0.1f, root * 1.8f, 1f);
            }
        }

        for (int i = y + root - 2; i < y + base + 2; i++) {
            world.setBlockState(new BlockPos(x, i, z), blockLog.getStateFromMeta(metadataLog), 2);
            world.setBlockState(new BlockPos(x + 1, i, z + 1), blockLog.getStateFromMeta(metadataLog), 2);
        }

        float horDir, verDir;
        int eX, eY, eZ;
        for (int j = 0; j < branch; j++) {
            horDir = (80 * j) - 40 + rand.nextInt(80);
            verDir = verStart + rand.nextFloat() * verRand;
            generateBranch(world, rand, (float) x + 0.5f, y + base, (float) z + 0.5f, horDir, verDir, length, 1f);

            eX = (int) (cos(horDir * PI / 180D) * verDir * length);
            eZ = (int) (sin(horDir * PI / 180D) * verDir * length);
            eY = (int) ((1f - verDir) * length);

            for (int m = 0; m < 2; m++) {
                generateLeaves(world, rand, x + eX - 2 + rand.nextInt(5), y + base + eY + rand.nextInt(2), z + eZ - 2 + rand.nextInt(5), 4f, 1.5f);
            }

            eX *= 0.8f;
            eY *= 0.8f;
            eZ *= 0.8f;

            generateLeaves(world, rand, x + eX, y + base + eY, z + eZ, 3f, 1.5f);
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

    public void generateLeaves(World world, Random rand, float x, float y, float z, float size, float width) {
        float dist;
        int i, j, k, s = (int) (size - 1f), w = (int) ((size - 1f) * 1.5f);
        for (i = -w; i <= w; i++) {
            for (j = -s + 1; j <= s; j++) {
                for (k = -w; k <= w; k++) {
                    dist = abs((float) i / 1.5f) + (float) abs(j) + abs((float) k / 1.5f);
                    if (dist <= size - 0.5f || (dist <= size && rand.nextBoolean())) {
                        if (dist < 1.3f) {
                            world.setBlockState(new BlockPos((int) x + i, (int) y + j, (int) z + k), blockLog.getStateFromMeta(metadataLog), 2);
                        }
                        if (world.isAirBlock(new BlockPos((int) x + i, (int) y + j, (int) z + k))) {
                            world.setBlockState(new BlockPos((int) x + i, (int) y + j, (int) z + k), blockLeaves.getStateFromMeta(metadataLeaves), 2);
                        }
                    }
                }
            }
        }
    }
}
