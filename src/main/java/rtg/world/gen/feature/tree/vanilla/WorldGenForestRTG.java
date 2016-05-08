package rtg.world.gen.feature.tree.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenForestRTG extends WorldGenForest
{
	public Block logBlock = Blocks.log;
	public byte logMeta = 0;
	public Block leavesBlock = Blocks.leaves;
	public byte leavesMeta = 0;
	
    private boolean field_150531_a;
    private static final String __OBFID = "CL_00000401";

    public WorldGenForestRTG()
    {
        this(false, false);
    }
    
    public WorldGenForestRTG(boolean doBlockNotify, boolean p_i45449_2_)
    {
        super(doBlockNotify, p_i45449_2_);
        this.field_150531_a = p_i45449_2_;
    }

    public boolean generate(World world, Random rand, int worldX, int worldY, int worldZ)
    {
        int l = rand.nextInt(3) + 5;

        if (this.field_150531_a)
        {
            l += rand.nextInt(7);
        }

        boolean flag = true;

        if (worldY >= 1 && worldY + l + 1 <= 256)
        {
            int j1;
            int k1;

            for (int i1 = worldY; i1 <= worldY + 1 + l; ++i1)
            {
                byte b0 = 1;

                if (i1 == worldY)
                {
                    b0 = 0;
                }

                if (i1 >= worldY + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (j1 = worldX - b0; j1 <= worldX + b0 && flag; ++j1)
                {
                    for (k1 = worldZ - b0; k1 <= worldZ + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            Block block = world.getBlock(j1, i1, k1);

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

                boolean isSoil = block2.canSustainPlant(world, worldX, worldY - 1, worldZ, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && worldY < 256 - l - 1)
                {
                    block2.onPlantGrow(world, worldX, worldY - 1, worldZ, worldX, worldY, worldZ);
                    int k2;

                    for (k2 = worldY - 3 + l; k2 <= worldY + l; ++k2)
                    {
                        j1 = k2 - (worldY + l);
                        k1 = 1 - j1 / 2;

                        for (int l2 = worldX - k1; l2 <= worldX + k1; ++l2)
                        {
                            int l1 = l2 - worldX;

                            for (int i2 = worldZ - k1; i2 <= worldZ + k1; ++i2)
                            {
                                int j2 = i2 - worldZ;

                                if (Math.abs(l1) != k1 || Math.abs(j2) != k1 || rand.nextInt(2) != 0 && j1 != 0)
                                {
                                    Block block1 = world.getBlock(l2, k2, i2);

                                    if (block1.isAir(world, l2, k2, i2) || block1.isLeaves(world, l2, k2, i2))
                                    {
                                        try {
                                        this.setBlockAndNotifyAdequately(world, l2, k2, i2, this.leavesBlock, this.leavesMeta);
                                        } catch (Exception e) {
                                            throw new RuntimeException(leavesBlock.getLocalizedName() + " "+ leavesMeta);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (k2 = 0; k2 < l; ++k2)
                    {
                        Block block3 = world.getBlock(worldX, worldY + k2, worldZ);

                        if (block3.isAir(world, worldX, worldY + k2, worldZ) || block3.isLeaves(world, worldX, worldY + k2, worldZ))
                        {
                            this.setBlockAndNotifyAdequately(world, worldX, worldY + k2, worldZ, this.logBlock, this.logMeta);
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
}