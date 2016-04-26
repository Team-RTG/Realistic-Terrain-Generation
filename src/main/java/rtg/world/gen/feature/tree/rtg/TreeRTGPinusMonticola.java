package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TreeRTGPinusMonticola extends TreeRTG
{
	private int height;
	
    public TreeRTGPinusMonticola()
    {
        super();
    }

    @Override
    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
    	this.height = this.trunkSize + this.crownSize;
    	
        int l = p_76484_2_.nextInt(this.height * 2) + this.height * 2;
        int i1 = this.height + p_76484_2_.nextInt(this.height);
        int j1 = l - i1;
        int k1 = 2 + p_76484_2_.nextInt(2);
        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256)
        {
            int i2;
            int l3;

            for (int l1 = p_76484_4_; l1 <= p_76484_4_ + 1 + l && flag; ++l1)
            {
                boolean flag1 = true;

                if (l1 - p_76484_4_ < i1)
                {
                    l3 = 0;
                }
                else
                {
                    l3 = k1;
                }

                for (i2 = p_76484_3_ - l3; i2 <= p_76484_3_ + l3 && flag; ++i2)
                {
                    for (int j2 = p_76484_5_ - l3; j2 <= p_76484_5_ + l3 && flag; ++j2)
                    {
                        if (l1 >= 0 && l1 < 256)
                        {
                            Block block = p_76484_1_.getBlock(i2, l1, j2);

                            if (!block.isAir(p_76484_1_, i2, l1, j2) && !block.isLeaves(p_76484_1_, i2, l1, j2) && block != Blocks.snow_layer)
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
                Block block1 = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ - 1, p_76484_5_);

                boolean isSoil = block1.canSustainPlant(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && p_76484_4_ < 256 - l - 1)
                {
                    block1.onPlantGrow(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, p_76484_3_, p_76484_4_, p_76484_5_);
                    l3 = p_76484_2_.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4)
                    {
                        k2 = p_76484_4_ + l - i4;

                        for (int l2 = p_76484_3_ - l3; l2 <= p_76484_3_ + l3; ++l2)
                        {
                            int i3 = l2 - p_76484_3_;

                            for (int j3 = p_76484_5_ - l3; j3 <= p_76484_5_ + l3; ++j3)
                            {
                                int k3 = j3 - p_76484_5_;

                                if ((Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0) && p_76484_1_.getBlock(l2, k2, j3).canBeReplacedByLeaves(p_76484_1_, l2, k2, j3))
                                {
                                    p_76484_1_.setBlock(l2, k2, j3, this.leavesBlock, this.leavesMeta, 0);
                                }
                            }
                        }

                        if (l3 >= i2)
                        {
                            l3 = b0;
                            b0 = 1;
                            ++i2;

                            if (i2 > k1)
                            {
                                i2 = k1;
                            }
                        }
                        else
                        {
                            ++l3;
                        }
                    }

                    i4 = p_76484_2_.nextInt(3);

                    for (k2 = 0; k2 < l - i4; ++k2)
                    {
                        Block block2 = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ + k2, p_76484_5_);

                        if (block2.isAir(p_76484_1_, p_76484_3_, p_76484_4_ + k2, p_76484_5_) || block2.isLeaves(p_76484_1_, p_76484_3_, p_76484_4_ + k2, p_76484_5_) || block2 == Blocks.snow_layer)
                        {
                        	p_76484_1_.setBlock(p_76484_3_, p_76484_4_ + k2, p_76484_5_, this.logBlock, this.logMeta, 0);
                        }
                    }
                    
                    if(this.height > 4)
                    {
                    	buildTrunk(p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_);
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
    
    @Override
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {
    	int[] pos = new int[]{0,0, 1,0, 0,1, -1,0, 0,-1};
    	int sh;
    	Block b;
    	for(int t = 0; t < (pos.length / 2); t++)
    	{    	
    		sh = rand.nextInt(4) + y - 2;
    		while(sh > y - 1)
    		{
    			if(world.getBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1]) == Blocks.grass)
    			{
    				break;
    			}
    			world.setBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1], this.logBlock, this.logMeta + 12, 0);
    			sh--;
    		}
    	}
    }
    
	@Override
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize)
    {
    	if(logLength == 3 && Math.abs(dX) + Math.abs(dZ) == 2)
    	{
    		logLength--;
    	}
    	
    	for(int i = -1; i <= 1; i++)
    	{
    		for(int j = -1; j <= 1; j++)
    		{
    			for(int k = 0; k < 2; k++)
    			{
    				if(Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1)
    				{
        				buildLeaves(world, x + i+ (dX * logLength), y + k, z + j + (dZ * logLength));
    				}
    			}
    		}
    	}
    	
    	for(int m = 1; m <= logLength; m++)
    	{
        	world.setBlock(x + (dX * m), y, z + (dZ * m), this.logBlock, this.logMeta, 0);
    	}
    }
	
	@Override
    public void buildLeaves(World world, int x, int y, int z)
    {
		if (!this.noLeaves) {
		
	    	Block b = world.getBlock(x, y, z);
	    	if(b.getMaterial() == Material.air)
	    	{
	    		world.setBlock(x, y, z, this.leavesBlock, this.leavesMeta, 0);
	    	}
		}
    }
}