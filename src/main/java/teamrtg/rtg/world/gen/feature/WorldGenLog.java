package teamrtg.rtg.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.block.material.Material.*;
import static net.minecraft.init.Blocks.log2;

public class WorldGenLog extends WorldGenerator {
    private int logMeta;
    private int leavesMeta;
    private Block logBlock;
    private Block leavesBlock;
    private int logLength;

    public WorldGenLog(int meta, int length, boolean leaves) {
        logBlock = meta > 4 ? log2 : Blocks.log;
        leavesBlock = meta > 4 ? Blocks.leaves2 : Blocks.leaves;

        logMeta = 1;
        leavesMeta = leaves ? 1 : -1;
        logLength = length < 2 ? 2 : length;
    }

    /**
     * @param blockLog
     * @param metaLog
     * @param blockLeaves
     * @param metaLeaves  Set to -1 to disable leaves.
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
        if (g.getMaterial(g.getDefaultState()) != ground && g.getMaterial(g.getDefaultState()) != grass && g.getMaterial(g.getDefaultState()) != sand && g.getMaterial(g.getDefaultState()) != rock) {
            return false;
        }

        int dir = rand.nextInt(2);
        int dirMeta = 4 + (dir * 4) + logMeta;
        boolean leaves = leavesMeta > -1;

        int i;
        Block b;
        int air = 0;
        for (i = 0; i < logLength; i++) {
            b = world.getBlockState(new BlockPos(x - (dir == 0 ? 1 : 0), y, z - (dir == 1 ? 1 : 0))).getBlock();
            if (b.getMaterial(b.getDefaultState()) != Material.air && b.getMaterial(b.getDefaultState()) != vine && b.getMaterial(b.getDefaultState()) != plants) {
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
            if (b.getMaterial(b.getDefaultState()) != Material.air && b.getMaterial(b.getDefaultState()) != vine && b.getMaterial(b.getDefaultState()) != plants) {
                break;
            }

            air += airCheck(world, rand, x, y, z);
            if (air > 2) {
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
        if (b.getMaterial(b.getDefaultState()) == air || b.getMaterial(b.getDefaultState()) == vine || b.getMaterial(b.getDefaultState()) == water || b.getMaterial(b.getDefaultState()) == plants) {
            b = world.getBlockState(new BlockPos(x, y - 2, z)).getBlock();
            if (b.getMaterial(b.getDefaultState()) == air || b.getMaterial(b.getDefaultState()) == vine || b.getMaterial(b.getDefaultState()) == water || b.getMaterial(b.getDefaultState()) == plants) {
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
            if ((b.getMaterial(b.getDefaultState()) == air || b.getMaterial(b.getDefaultState()) == vine || b.getMaterial(b.getDefaultState()) == plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z - 1), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
            b = world.getBlockState(new BlockPos(x, y, z + 1)).getBlock();
            if ((b.getMaterial(b.getDefaultState()) == air || b.getMaterial(b.getDefaultState()) == vine || b.getMaterial(b.getDefaultState()) == plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z + 1), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
        } else {
            b = world.getBlockState(new BlockPos(x - 1, y, z)).getBlock();
            if ((b.getMaterial(b.getDefaultState()) == air || b.getMaterial(b.getDefaultState()) == vine || b.getMaterial(b.getDefaultState()) == plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x - 1, y, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
            b = world.getBlockState(new BlockPos(x + 1, y, z)).getBlock();
            if ((b.getMaterial(b.getDefaultState()) == air || b.getMaterial(b.getDefaultState()) == vine || b.getMaterial(b.getDefaultState()) == plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x + 1, y, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
            }
        }

        b = world.getBlockState(new BlockPos(x, y + 1, z)).getBlock();
        if ((b.getMaterial(b.getDefaultState()) == air || b.getMaterial(b.getDefaultState()) == vine || b.getMaterial(b.getDefaultState()) == plants) && rand.nextInt(3) == 0) {
            world.setBlockState(new BlockPos(x, y + 1, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
        }
    }
}
