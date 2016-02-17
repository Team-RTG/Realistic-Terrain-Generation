package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGPine extends WorldGenerator
{
	private int height;
	private int metadata;
	
    public WorldGenTreeRTGPine(int h, int m)
    {
        super(false);
        
        height = h;
        metadata = m;
    }

    public boolean generate(World p_76484_1_, Random p_76484_2_, BlockPos blockPos)
    {
        int l = p_76484_2_.nextInt(height * 2) + height * 2;
        int i1 = height + p_76484_2_.nextInt(height);
        int j1 = l - i1;
        int k1 = 2 + p_76484_2_.nextInt(2);
        boolean flag = true;

        if (blockPos.getY() >= 1 && blockPos.getY() + l + 1 <= 256)
        {
            int i2;
            int l3;

            for (int l1 = blockPos.getY(); l1 <= blockPos.getY() + 1 + l && flag; ++l1)
            {
                boolean flag1 = true;

                if (l1 - blockPos.getY() < i1)
                {
                    l3 = 0;
                }
                else
                {
                    l3 = k1;
                }

                for (i2 = blockPos.getX() - l3; i2 <= blockPos.getX() + l3 && flag; ++i2)
                {
                    for (int j2 = blockPos.getZ() - l3; j2 <= blockPos.getZ() + l3 && flag; ++j2)
                    {
                        if (l1 >= 0 && l1 < 256)
                        {
                            Block block = p_76484_1_.getBlockState(new BlockPos(i2, l1, j2)).getBlock();

                            if (!block.isAir(p_76484_1_, new BlockPos(i2, l1, j2)) && !block.isLeaves(p_76484_1_, new BlockPos(i2, l1, j2)) && block != Blocks.snow_layer)
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
                Block block1 = p_76484_1_.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock();

                boolean isSoil = block1.canSustainPlant(p_76484_1_, new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()), EnumFacing.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && blockPos.getY() < 256 - l - 1)
                {
                    block1.onPlantGrow(p_76484_1_, new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()), new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
                    l3 = p_76484_2_.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4)
                    {
                        k2 = blockPos.getY() + l - i4;

                        for (int l2 = blockPos.getX() - l3; l2 <= blockPos.getX() + l3; ++l2)
                        {
                            int i3 = l2 - blockPos.getX();

                            for (int j3 = blockPos.getZ() - l3; j3 <= blockPos.getZ() + l3; ++j3)
                            {
                                int k3 = j3 - blockPos.getZ();

                                if ((Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0) && p_76484_1_.getBlockState(new BlockPos(l2, k2, j3)).getBlock().canBeReplacedByLeaves(p_76484_1_, new BlockPos(l2, k2, j3)))
                                {
                                    p_76484_1_.setBlockState(new BlockPos(l2, k2, j3), Blocks.leaves.getStateFromMeta(metadata),2);
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
                        Block block2 = p_76484_1_.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + k2, blockPos.getZ())).getBlock();

                        if (block2.isAir(p_76484_1_, new BlockPos(blockPos.getX(), blockPos.getY() + k2, blockPos.getZ())) || block2.isLeaves(p_76484_1_, new BlockPos(blockPos.getX(), blockPos.getY() + k2, blockPos.getZ())) || block2 == Blocks.snow_layer)
                        {
                        	p_76484_1_.setBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + k2, blockPos.getZ()), Blocks.log.getStateFromMeta(metadata),2);
                        }
                    }
                    
                    if(height > 4)
                    {
                    	createTrunk(p_76484_1_, p_76484_2_, blockPos.getX(), blockPos.getY(), blockPos.getZ());
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
    
    private void createTrunk(World world, Random rand, int x, int y, int z)
    {
    	int[] pos = new int[]{0,0, 1,0, 0,1, -1,0, 0,-1};
    	int sh;
    	Block b;
    	for(int t = 0; t < 5; t++)
    	{    	
    		sh = rand.nextInt(4) + y - 2;
    		while(sh > y - 1)
    		{
    			if(world.getBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1])).getBlock() == Blocks.grass)
    			{
    				break;
    			}
    			world.setBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1]), Blocks.log.getStateFromMeta(12),2);
    			sh--;
    		}
    	}
    }
}