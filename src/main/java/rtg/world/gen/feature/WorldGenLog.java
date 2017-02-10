package rtg.world.gen.feature;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.util.WorldUtil;

public class WorldGenLog extends WorldGenerator {

    private IBlockState logBlock;
    private IBlockState leavesBlock;
    private int logLength;
    private boolean generateLeaves;

    /**
     * @param logBlock
     * @param leavesBlock
     * @param logLength
     */
    public WorldGenLog(IBlockState logBlock, IBlockState leavesBlock, int logLength) {

        this.logBlock = logBlock;
        this.leavesBlock = leavesBlock;
        this.logLength = logLength;

        this.generateLeaves = false;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        return this.generate(world, rand, pos.getX(), pos.getY(), pos.getZ());
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {

        IBlockState g = world.getBlockState(new BlockPos(x, y - 1, z));
        if (g.getBlock().getMaterial() != Material.ground && g.getBlock().getMaterial() != Material.grass && g.getBlock().getMaterial() != Material.sand && g.getBlock().getMaterial() != Material.rock) {
            return false;
        }

        WorldUtil worldUtil = new WorldUtil(world);
        int dir = rand.nextInt(2);
        int dirMeta = 4 + (dir * 4) + this.logBlock.getBlock().getMetaFromState(this.logBlock);

        int i;
        IBlockState b;
        int air = 0;

        ArrayList<Integer> aX = new ArrayList<Integer>();
        ArrayList<Integer> aY = new ArrayList<Integer>();
        ArrayList<Integer> aZ = new ArrayList<Integer>();
        ArrayList<IBlockState> aBlock = new ArrayList<IBlockState>();
        for (i = 0; i < logLength; i++) {
            b = world.getBlockState(new BlockPos(x - (dir == 0 ? 1 : 0), y, z - (dir == 1 ? 1 : 0)));
            if (b.getBlock().getMaterial() != Material.air && b.getBlock().getMaterial() != Material.vine && b.getBlock().getMaterial() != Material.plants) {
                break;
            }

            x -= dir == 0 ? 1 : 0;
            z -= dir == 1 ? 1 : 0;

            if (airCheck(world, rand, x, y, z) > 0) {
                return false;
            }
        }

        for (i = 0; i < logLength * 2; i++) {
            b = world.getBlockState(new BlockPos(x + (dir == 0 ? 1 : 0), y, z + (dir == 1 ? 1 : 0)));
            if (b.getBlock().getMaterial() != Material.air && b.getBlock().getMaterial() != Material.vine && b.getBlock().getMaterial() != Material.plants) {
                break;
            }

            air += airCheck(world, rand, x, y, z);
            if (air > 2) {
                return false;
            }

            /**
             * Before we place the log block, let's make sure that there's an air block immediately above it.
             * This is to ensure that the log doesn't override, for example, a 2-block tall plant,
             * which some mods (like WAILA) have trouble handling.
             *
             * Also, to ensure that we don't have 'broken' logs, if one log block fails the check,
             * then no logs actually get placed.
             */
            if (!worldUtil.isBlockAbove(Blocks.air.getDefaultState(), 1, world, x, y, z, true)) {
                //Logger.debug("Found non-air block above log at %d %d %d", x, y, z);
                return false;
            }

            // Store the log information instead of placing it straight away.
            aX.add(x);
            aY.add(y);
            aZ.add(z);
            aBlock.add(logBlock.getBlock().getStateFromMeta(dirMeta));

            if (this.generateLeaves) {
                addLeaves(world, rand, dir, x, y, z);
            }

            x += dir == 0 ? 1 : 0;
            z += dir == 1 ? 1 : 0;
        }

        for (int i1 = 0; i1 < aBlock.size(); i1++) {
            world.setBlockState(new BlockPos(aX.get(i1).intValue(), aY.get(i1).intValue(), aZ.get(i1).intValue()), aBlock.get(i1), 2);
        }

        return true;
    }

    private int airCheck(World world, Random rand, int x, int y, int z) {

        IBlockState b = world.getBlockState(new BlockPos(x, y - 1, z));
        if (b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine || b.getBlock().getMaterial() == Material.water || b.getBlock().getMaterial() == Material.plants) {
            b = world.getBlockState(new BlockPos(x, y - 2, z));
            if (b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine || b.getBlock().getMaterial() == Material.water || b.getBlock().getMaterial() == Material.plants) {
                return 99;
            }
            return 1;
        }

        return 0;
    }

    private void addLeaves(World world, Random rand, int dir, int x, int y, int z) {

        IBlockState b;
        if (dir == 0) {
            b = world.getBlockState(new BlockPos(x, y, z - 1));
            if ((b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine || b.getBlock().getMaterial() == Material.plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z - 1), leavesBlock, 2);
            }
            b = world.getBlockState(new BlockPos(x, y, z + 1));
            if ((b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine || b.getBlock().getMaterial() == Material.plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z + 1), leavesBlock, 2);
            }
        }
        else {
            b = world.getBlockState(new BlockPos(x - 1, y, z));
            if ((b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine || b.getBlock().getMaterial() == Material.plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x - 1, y, z), leavesBlock, 2);
            }
            b = world.getBlockState(new BlockPos(x + 1, y, z));
            if ((b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine || b.getBlock().getMaterial() == Material.plants) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x + 1, y, z), leavesBlock, 2);
            }
        }

        b = world.getBlockState(new BlockPos(x, y + 1, z));
        if ((b.getBlock().getMaterial() == Material.air || b.getBlock().getMaterial() == Material.vine || b.getBlock().getMaterial() == Material.plants) && rand.nextInt(3) == 0) {
            world.setBlockState(new BlockPos(x, y + 1, z), leavesBlock, 2);
        }
    }
}
