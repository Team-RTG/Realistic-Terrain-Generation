package teamrtg.rtg.api.tools.feature;

import static net.minecraft.block.material.Material.AIR;
import static net.minecraft.block.material.Material.GRASS;
import static net.minecraft.block.material.Material.GROUND;
import static net.minecraft.block.material.Material.PLANTS;
import static net.minecraft.block.material.Material.ROCK;
import static net.minecraft.block.material.Material.SAND;
import static net.minecraft.block.material.Material.VINE;
import static net.minecraft.block.material.Material.WATER;
import static net.minecraft.init.Blocks.LOG2;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.util.WorldUtil;

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

        WorldUtil worldUtil = new WorldUtil(world);
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

        ArrayList<BlockPos> aBlockPos = new ArrayList<BlockPos>();
        ArrayList<IBlockState> aBlockState = new ArrayList<IBlockState>();
        for (i = 0; i < logLength * 2; i++) {
            b = world.getBlockState(new BlockPos(x + (dir == 0 ? 1 : 0), y, z + (dir == 1 ? 1 : 0))).getBlock();
            if (b.getMaterial(b.getDefaultState()) != Material.AIR && b.getMaterial(b.getDefaultState()) != VINE && b.getMaterial(b.getDefaultState()) != PLANTS) {
                break;
            }

            AIR += airCheck(world, rand, x, y, z);
            if (AIR > 2) {
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
            if (!worldUtil.isBlockAbove(Blocks.AIR.getDefaultState(), 1, world, x, y, z, true)) {
            	return false;
            }
            
            // Store the log information instead of placing it straight away.
            aBlockPos.add(new BlockPos(x, y, z));
            aBlockState.add(logBlock.getStateFromMeta(dirMeta));

            if (leavesMeta > -1) {
                addLeaves(world, rand, dir, x, y, z);
            }

            x += dir == 0 ? 1 : 0;
            z += dir == 1 ? 1 : 0;
        }
        
        for (int i1 = 0; i1 < aBlockPos.size(); i1++) {
        	world.setBlockState(aBlockPos.get(i1), aBlockState.get(i1), 2);
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
