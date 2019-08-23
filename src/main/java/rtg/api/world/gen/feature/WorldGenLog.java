package rtg.api.world.gen.feature;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.util.BlockUtil;
import rtg.api.util.BlockUtil.MatchType;
import rtg.api.util.Logger;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Random;


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
// TODO: [1.12] Fix fallen log generation to be more efficent; Stop using ArrayLists.
    public boolean generate(@Nonnull World world, @Nonnull Random rand, @Nonnull BlockPos pos) {

        int x = pos.getX(),
            y = pos.getY(),
            z = pos.getZ();

        IBlockState g = world.getBlockState(new BlockPos(x, y - 1, z));
        if (g.getMaterial() != Material.GROUND && g.getMaterial() != Material.GRASS && g.getMaterial() != Material.SAND && g.getMaterial() != Material.ROCK) {
            return false;
        }

        int dir = rand.nextInt(2); // The direction of the log (0 = X; 1 = Z)
        IBlockState b;
        int air = 0;

        ArrayList<Integer> aX = new ArrayList<Integer>();
        ArrayList<Integer> aY = new ArrayList<Integer>();
        ArrayList<Integer> aZ = new ArrayList<Integer>();
        ArrayList<IBlockState> aBlock = new ArrayList<IBlockState>();

        MutableBlockPos mpos = new MutableBlockPos(pos);
        for (int i = 0; i < logLength; i++) {
            b = world.getBlockState(mpos.setPos(x - (dir == 0 ? 1 : 0), y, z - (dir == 1 ? 1 : 0)));

            if (b.getMaterial() != Material.AIR && b.getMaterial() != Material.VINE && b.getMaterial() != Material.PLANTS) {
                break;
            }

            x -= dir == 0 ? 1 : 0;
            z -= dir == 1 ? 1 : 0;

            if (airCheck(world, x, y, z) > 0) {
                return false;
            }
        }

        for (int i = 0; i < logLength * 2; i++) {
            b = world.getBlockState(mpos.setPos(x + (dir == 0 ? 1 : 0), y, z + (dir == 1 ? 1 : 0)));

            if (b.getMaterial() != Material.AIR && b.getMaterial() != Material.VINE && b.getMaterial() != Material.PLANTS) {
                break;
            }

            air += airCheck(world, x, y, z);
            if (air > 2) {
                return false;
            }

            /*
             * Before we place the log block, let's make sure that there's an air block immediately above it.
             * This is to ensure that the log doesn't override, for example, a 2-block tall plant,
             * which some mods (like WAILA) have trouble handling.
             *
             * Also, to ensure that we don't have 'broken' logs, if one log block fails the check,
             * then no logs actually get placed.
             */
            if (!BlockUtil.checkVerticalBlocks(MatchType.ALL, world, pos, 1, Blocks.AIR)) {
                return false;
            }

            // Store the log information instead of placing it straight away.
            aX.add(x);
            aY.add(y);
            aZ.add(z);

            // If we can't rotate the log block for whatever reason, then don't even try placing it and bail.
            // We'd rather generate nothing than something ugly.
            try {
                aBlock.add(logBlock.withProperty(BlockLog.LOG_AXIS, (dir == 0 ? BlockLog.EnumAxis.X : BlockLog.EnumAxis.Z)));
            }
            catch (Exception e) {
                //aBlock.add(logBlock);
                //Logger.error(e.getMessage());
                return false;
            }

            if (this.generateLeaves) {
                addLeaves(world, rand, dir, x, y, z);
            }

            x += dir == 0 ? 1 : 0;
            z += dir == 1 ? 1 : 0;
        }

        for (int i = 0; i < aBlock.size(); i++) {
            world.setBlockState(mpos.setPos(aX.get(i), aY.get(i), aZ.get(i)), aBlock.get(i), 2);
        }

        return true;
    }

    private int airCheck(World world, int x, int y, int z) {

        IBlockState b = world.getBlockState(new BlockPos(x, y - 1, z));
        if (b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.WATER || b.getMaterial() == Material.PLANTS) {
            b = world.getBlockState(new BlockPos(x, y - 2, z));
            if (b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.WATER || b.getMaterial() == Material.PLANTS) {
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
            if ((b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z - 1), leavesBlock, 2);
            }
            b = world.getBlockState(new BlockPos(x, y, z + 1));
            if ((b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x, y, z + 1), leavesBlock, 2);
            }
        }
        else {
            b = world.getBlockState(new BlockPos(x - 1, y, z));
            if ((b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x - 1, y, z), leavesBlock, 2);
            }
            b = world.getBlockState(new BlockPos(x + 1, y, z));
            if ((b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.PLANTS) && rand.nextInt(3) == 0) {
                world.setBlockState(new BlockPos(x + 1, y, z), leavesBlock, 2);
            }
        }

        b = world.getBlockState(new BlockPos(x, y + 1, z));
        if ((b.getMaterial() == Material.AIR || b.getMaterial() == Material.VINE || b.getMaterial() == Material.PLANTS) && rand.nextInt(3) == 0) {
            world.setBlockState(new BlockPos(x, y + 1, z), leavesBlock, 2);
        }
    }

    public IBlockState getLogBlock() {

        return logBlock;
    }

    public WorldGenLog setLogBlock(IBlockState logBlock) {

        this.logBlock = logBlock;
        return this;
    }

    public IBlockState getLeavesBlock() {

        return leavesBlock;
    }

    public WorldGenLog setLeavesBlock(IBlockState leavesBlock) {

        this.leavesBlock = leavesBlock;
        return this;
    }
}
