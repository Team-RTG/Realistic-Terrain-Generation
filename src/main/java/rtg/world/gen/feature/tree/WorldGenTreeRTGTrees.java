package rtg.world.gen.feature.tree;

import java.util.Random;

import rtg.config.rtg.ConfigRTG;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTreeRTGTrees extends WorldGenTrees
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;
    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;
    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;
    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;
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
    public boolean generate(World world, Random rand, int worldX, int worldY, int worldZ)
    {
        int l = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

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
                            block = world.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(world, j1, i1, k1))
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
                Block block2 = world.getBlock(worldX, worldY - 1, worldZ);

                if (block2 == Blocks.sand && !ConfigRTG.allowTreesToGenerateOnSand) {
                    return false;
                }
                
                boolean isSoil = block2.canSustainPlant(world, worldX, worldY - 1, worldZ, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && worldY < 256 - l - 1)
                {
                    block2.onPlantGrow(world, worldX, worldY - 1, worldZ, worldX, worldY, worldZ);
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
                                    Block block1 = world.getBlock(i2, k1, k2);

                                    if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2))
                                    {
                                        this.setBlockAndNotifyAdequately(world, i2, k1, k2, Blocks.leaves, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = world.getBlock(worldX, worldY + k1, worldZ);

                        if (block.isAir(world, worldX, worldY + k1, worldZ) || block.isLeaves(world, worldX, worldY + k1, worldZ))
                        {
                            this.setBlockAndNotifyAdequately(world, worldX, worldY + k1, worldZ, Blocks.log, this.metaWood);

                            if (this.vinesGrow && k1 > 0)
                            {
                                if (rand.nextInt(3) > 0 && world.isAirBlock(worldX - 1, worldY + k1, worldZ))
                                {
                                    this.setBlockAndNotifyAdequately(world, worldX - 1, worldY + k1, worldZ, Blocks.vine, 8);
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(worldX + 1, worldY + k1, worldZ))
                                {
                                    this.setBlockAndNotifyAdequately(world, worldX + 1, worldY + k1, worldZ, Blocks.vine, 2);
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(worldX, worldY + k1, worldZ - 1))
                                {
                                    this.setBlockAndNotifyAdequately(world, worldX, worldY + k1, worldZ - 1, Blocks.vine, 1);
                                }

                                if (rand.nextInt(3) > 0 && world.isAirBlock(worldX, worldY + k1, worldZ + 1))
                                {
                                    this.setBlockAndNotifyAdequately(world, worldX, worldY + k1, worldZ + 1, Blocks.vine, 4);
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
                                    if (world.getBlock(i2, k1, j2).isLeaves(world, i2, k1, j2))
                                    {
                                        if (rand.nextInt(4) == 0 && world.getBlock(i2 - 1, k1, j2).isAir(world, i2 - 1, k1, j2))
                                        {
                                            this.growVines(world, i2 - 1, k1, j2, 8);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlock(i2 + 1, k1, j2).isAir(world, i2 + 1, k1, j2))
                                        {
                                            this.growVines(world, i2 + 1, k1, j2, 2);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlock(i2, k1, j2 - 1).isAir(world, i2, k1, j2 - 1))
                                        {
                                            this.growVines(world, i2, k1, j2 - 1, 1);
                                        }

                                        if (rand.nextInt(4) == 0 && world.getBlock(i2, k1, j2 + 1).isAir(world, i2, k1, j2 + 1))
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
                                        this.setBlockAndNotifyAdequately(world, worldX + Direction.offsetX[Direction.rotateOpposite[i3]], worldY + l - 5 + k1, worldZ + Direction.offsetZ[Direction.rotateOpposite[i3]], Blocks.cocoa, l1 << 2 | i3);
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
        this.setBlockAndNotifyAdequately(world, worldX, worldY, worldZ, Blocks.vine, vineLength);
        int i1 = 4;

        while (true)
        {
            --worldY;

            if (!world.getBlock(worldX, worldY, worldZ).isAir(world, worldX, worldY, worldZ) || i1 <= 0)
            {
                return;
            }

            this.setBlockAndNotifyAdequately(world, worldX, worldY, worldZ, Blocks.vine, vineLength);
            --i1;
        }
    }
}