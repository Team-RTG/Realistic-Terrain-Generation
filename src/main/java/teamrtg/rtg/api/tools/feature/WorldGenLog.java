package teamrtg.rtg.api.tools.feature;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.block.material.Material.*;
import static net.minecraft.init.Blocks.LOG2;

public class WorldGenLog extends WorldGenerator {
    private int logMeta;
    private int leavesMeta;
    private Block logBlock;
    private Block leavesBlock;
    private int logLength;

    public WorldGenLog(int meta, int length, boolean LEAVES) {
        logBlock = meta > 4 ? LOG2 : Blocks.LOG;
        leavesBlock = meta > 4 ? Blocks.LEAVES2 : Blocks.LEAVES;

        logMeta = 1;
        leavesMeta = LEAVES ? 1 : -1;
        logLength = length < 2 ? 2 : length;
    }

    /**
     * @param blockLog
     * @param metaLog
     * @param blockLeaves
     * @param metaLeaves  Set to -1 to disable LEAVES.
     * @param length
     */
    public WorldGenLog(Block blockLog, int metaLog, Block blockLeaves, int metaLeaves, int length) {
        logBlock = blockLog;
        leavesBlock = blockLeaves;
        logMeta = metaLog;
        leavesMeta = metaLeaves;
        logLength = length;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos) {
        return this.generate(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block g = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
        if (g.getMaterial(g.getDefaultState()) != GROUND && g.getMaterial(g.getDefaultState()) != GRASS && g.getMaterial(g.getDefaultState()) != SAND && g.getMaterial(g.getDefaultState()) != ROCK) {
            return false;
        }

        int dir = rand.nextInt(2);
        int dirMeta = 4 + (dir * 4) + logMeta;
        boolean LEAVES = leavesMeta > -1;

        int i;
        Block b;
        int AIR = 0;
        for (i = 0; i < logLength; i++) {
            b = world.getBlockState(new BlockPos(x - (dir == 0 ? 1 : 0), y, z - (dir == 1 ? 1 : 0))).getBlock();
            if (b.getMaterial(b.getDefaultState()) != Material.AIR && b.getMaterial(b.getDefaultState()) != VINE && b.getMaterial(b.getDefaultState()) != PLANTS) {
                break;
            }

            x -= dir == 0 ? 1 : 0;
            z -= dir == 1 ? 1 : 0;

            if (airCheck(world, rand, x, y, z) > 0) {
                return false;
            }
        }

        for (i = 0; i < logLength * 2; i++) {
            b = world.getBlockState(new BlockPos(x + (dir == 0 ? 1 : 0), y, z + (dir == 1 ? 1 : 0))).getBlock();
            if (b.getMaterial(b.getDefaultState()) != Material.AIR && b.getMaterial(b.getDefaultState()) != VINE && b.getMaterial(b.getDefaultState()) != PLANTS) {
                break;
            }

            AIR += airCheck(world, rand, x, y, z);
            if (AIR > 2) {
                return false;
            }

            world.setBlockState(new BlockPos(x, y, z), logBlock.getStateFromMeta(dirMeta), 0);

            if (leavesMeta > -1) {
                addLeaves(world, rand, dir, x, y, z);
            }

            x += dir == 0 ? 1 : 0;
            z += dir == 1 ? 1 : 0;
        }

        return true;
    }

    private int airCheck(World world, Random rand, int x, int y, int z) {
        Block b = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
        if (b.getMaterial(b.getDefaultState()) == AIR || b.getMaterial(b.getDefaultState()) == VINE || b.getMaterial(b.getDefaultState()) == WATER || b.getMaterial(b.getDefaultState()) == PLANTS) {
            b = world.getBlockState(new BlockPos(x, y - 2, z)).getBlock();
            if (b.getMaterial(b.getDefaultState()) == AIR || b.getMaterial(b.getDefaultState()) == VINE || b.getMaterial(b.getDefaultState()) == WATER || b.getMaterial(b.getDefaultState()) == PLANTS) {
                return 99;
            }
            return 1;
        }

        return 0;
    }

    private void addLeaves(World world, Random rand, int dir, int x, int y, int z) {
        Block b;
        if (dir == 0) {
            b = world.getBlockState(new BlockPos(x, y, z - 1)).getBlock();
            if ((b.getMaterial(b.getDefaultState()) == AIR || b.getMaterial(b.getDefaultState()) == VINE || b.getMaterial(b.getDefaultState()) == PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z - 1), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
            b = world.getBlockState(new BlockPos(x, y, z + 1)).getBlock();
            if ((b.getMaterial(b.getDefaultState()) == AIR || b.getMaterial(b.getDefaultState()) == VINE || b.getMaterial(b.getDefaultState()) == PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z + 1), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
        } else {
            b = world.getBlockState(new BlockPos(x - 1, y, z)).getBlock();
            if ((b.getMaterial(b.getDefaultState()) == AIR || b.getMaterial(b.getDefaultState()) == VINE || b.getMaterial(b.getDefaultState()) == PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x - 1, y, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
            b = world.getBlockState(new BlockPos(x + 1, y, z)).getBlock();
            if ((b.getMaterial(b.getDefaultState()) == AIR || b.getMaterial(b.getDefaultState()) == VINE || b.getMaterial(b.getDefaultState()) == PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x + 1, y, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
        }

        b = world.getBlockState(new BlockPos(x, y + 1, z)).getBlock();
        if ((b.getMaterial(b.getDefaultState()) == AIR || b.getMaterial(b.getDefaultState()) == VINE || b.getMaterial(b.getDefaultState()) == PLANTS) && rand.nextInt(3) == 0) {
            world.setBlockState(new BlockPos(x, y + 1, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
        }
    }
}
