package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import rtg.config.rtg.ConfigRTG;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class WorldGenTreeRTGTrees extends WorldGenTrees
{
    /** The minimum height of a generated tree. */
    private int minTreeHeight;
    /** True if this tree should grow Vines. */
    private boolean vinesGrow;
    /** The metadata value of the wood to use in tree generation. */
    private int metaWood;
    /** The metadata value of the leaves to use in tree generation. */
    private int metaLeaves;
    private static final String __OBFID = "CL_00000438";

    public WorldGenTreeRTGTrees(boolean doBlockNotify)
    {
        this(doBlockNotify, 4, 0, 0, false);
    }

    public WorldGenTreeRTGTrees(boolean doBlockNotify, int minTreeHeight, int metaWood, int metaLeaves, boolean vinesGrow)
    {
        super(doBlockNotify);
        this.minTreeHeight = minTreeHeight;
        this.metaWood = metaWood;
        this.metaLeaves = metaLeaves;
        this.vinesGrow = vinesGrow;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
        int l = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        int worldX = blockPos.getX();
        int worldY = blockPos.getY();
        int worldZ = blockPos.getZ();

        if (worldY >= 1 && worldY + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = worldY; i1 <= worldY + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == worldY)
                {
                    b0 = 0;
                }

                if (i1 >= worldY + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = worldX - b0; j1 <= worldX + b0 && flag; ++j1)
                {
                    for (k1 = worldZ - b0; k1 <= worldZ + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {

                            if (!this.isReplaceable(world, new BlockPos(j1, i1, k1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = world.getBlockState(new BlockPos(worldX, worldY - 1, worldZ)).getBlock();

                if (block2 == Blocks.sand && !ConfigRTG.allowTreesToGenerateOnSand) {
                    return false;
                }
                
                boolean isSoil = block2.canSustainPlant(world, new BlockPos(worldX, worldY - 1, worldZ), EnumFacing.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && worldY < 256 - l - 1)
                {
                    block2.onPlantGrow(world, new BlockPos(worldX, worldY - 1, worldZ), new BlockPos(worldX, worldY, worldZ));
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = worldY - b0 + l; k1 <= worldY + l; ++k1)
                    {
                        i3 = k1 - (worldY + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = worldX - l1; i2 <= worldX + l1; ++i2)
                        {
                            j2 = i2 - worldX;

                            for (int k2 = worldZ - l1; k2 <= worldZ + l1; ++k2)
                            {
                                int l2 = k2 - worldZ;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = world.getBlockState(new BlockPos(i2, k1, k2)).getBlock();

                                    if (block1.isAir(world, new BlockPos(i2, k1, k2)) || block1.isLeaves(world, new BlockPos(i2, k1, k2)))
                                    {
                                        this.setBlockAndNotifyAdequately(world, new BlockPos(i2, k1, k2), Blocks.leaves.getStateFromMeta(this.metaLeaves));
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = world.getBlockState(new BlockPos(worldX, worldY + k1, worldZ)).getBlock();

                        if (block.isAir(world, new BlockPos(worldX, worldY + k1, worldZ)) || block.isLeaves(world, new BlockPos(worldX, worldY + k1, worldZ)))
                        {
                            this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY + k1, worldZ), Blocks.log.getStateFromMeta(this.metaWood));

                            if (this.vinesGrow && k1 > 0)
                            {
                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX - 1, worldY + k1, worldZ)))
                                {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX - 1, worldY + k1, worldZ), Blocks.vine.getStateFromMeta(8));
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX + 1, worldY + k1, worldZ)))
                                {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX + 1, worldY + k1, worldZ), Blocks.vine.getStateFromMeta(2));
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX, worldY + k1, worldZ - 1)))
                                {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY + k1, worldZ - 1), Blocks.vine.getStateFromMeta(1));
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(worldX, worldY + k1, worldZ + 1)))
                                {
                                    this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY + k1, worldZ + 1), Blocks.vine.getStateFromMeta(4));
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (k1 = worldY - 3 + l; k1 <= worldY + l; ++k1)
                        {
                            i3 = k1 - (worldY + l);
                            l1 = 2 - i3 / 2;

                            for (i2 = worldX - l1; i2 <= worldX + l1; ++i2)
                            {
                                for (j2 = worldZ - l1; j2 <= worldZ + l1; ++j2)
                                {
                                    if (world.getBlockState(new BlockPos(i2, k1, j2)).getBlock().isLeaves(world, new BlockPos(i2, k1, j2)))
                                    {
                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2 - 1, k1, j2)).getBlock().isAir(world, new BlockPos(i2 - 1, k1, j2)))
                                        {
                                            this.growVines(world, i2 - 1, k1, j2, 8);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2 + 1, k1, j2)).getBlock().isAir(world, new BlockPos(i2 + 1, k1, j2)))
                                        {
                                            this.growVines(world, i2 + 1, k1, j2, 2);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2, k1, j2 - 1)).getBlock().isAir(world, new BlockPos(i2, k1, j2 - 1)))
                                        {
                                            this.growVines(world, i2, k1, j2 - 1, 1);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlockState(new BlockPos(i2, k1, j2 + 1)).getBlock().isAir(world, new BlockPos(i2, k1, j2 + 1)))
                                        {
                                            this.growVines(world, i2, k1, j2 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

                        if (rand.nextInt(5) == 0 && l > 5)
                        {
                            for (k1 = 0; k1 < 2; ++k1)
                            {
                                for (i3 = 0; i3 < 4; ++i3)
                                {
                                    if (rand.nextInt(4 - k1) == 0)
                                    {
                                        l1 = rand.nextInt(3);
                                        // TODO: I guess this places the cocoa on different sides of the log, but i need something to replace Direction.
                                        //this.setBlockAndNotifyAdequately(world, worldX + Direction.offsetX[Direction.rotateOpposite[i3]], worldY + l - 5 + k1, worldZ + Direction.offsetZ[Direction.rotateOpposite[i3]], Blocks.cocoa, l1 << 2 | i3);
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */    
    private void growVines(World world, int worldX, int worldY, int worldZ, int vineLength)
    {
        this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY, worldZ), Blocks.vine.getStateFromMeta(vineLength));
        int i1 = 4;

        while (true)
        {
            --worldY;

            if (!world.getBlockState(new BlockPos(worldX, worldY, worldZ)).getBlock().isAir(world, new BlockPos(worldX, worldY, worldZ)) || i1 <= 0)
            {
                return;
            }

            this.setBlockAndNotifyAdequately(world, new BlockPos(worldX, worldY, worldZ), Blocks.vine.getStateFromMeta(vineLength));
            --i1;
        }
    }
}