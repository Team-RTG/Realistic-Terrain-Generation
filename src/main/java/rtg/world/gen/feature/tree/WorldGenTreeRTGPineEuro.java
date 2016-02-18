package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGPineEuro extends WorldGenerator
{
	public WorldGenTreeRTGPineEuro()
	{
	}

    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
    	Block g = world.getBlockState(blockPos.down()).getBlock();
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	int height = 18 + rand.nextInt(10);
    	int leafheight = 7 + rand.nextInt(4);
    	float branchIncrease = 0.25f;
    	
    	for(int i = 0; i <= height; i++)
    	{
    		world.setBlockState(blockPos.up(), Blocks.log.getDefaultState(), 0);
    	}
    	createRandomLeaves(world, rand, blockPos.add(0, height ,0), 2);
    	createTrunk(world, rand, blockPos);
    	
    	int dir = 0, b;
    	float xd, yd, bl = 1f;
    	for(int j = height; j >= height - leafheight; j--)
    	{
    		bl += branchIncrease;
    		dir += rand.nextInt(180) + 90;
    		dir -= dir > 360 ? 360 : 0;
			xd = (float)Math.cos(dir * Math.PI / 180f);
			yd = (float)Math.sin(dir * Math.PI / 180f);
			
			for(b = 0; b <= bl; b++)
			{
				world.setBlockState(blockPos.add((int)(b * xd), j, (int)(b * yd)), Blocks.log.getStateFromMeta(12), 0);
			}
	    	createRandomLeaves(world, rand, blockPos.up(j), 2);
	    	createRandomLeaves(world, rand, blockPos.add((int)(b * xd), j, (int)(b * yd)), 2);
    	}
    	
    	return true;
    }
    
    private void createRandomLeaves(World world, Random rand, BlockPos blockPos, int size)
    {
    	int l;
    	int t = (int)Math.pow(size, 2);
    	for(int i = -size; i <= size; i++)
    	{
    		for(int j = -size; j <= size; j++)
    		{
    			for(int k = -size; k <= size; k++)
    			{
    				l = i*i + j*j + k*k;
    				if(l <= t)
    				{
    					if(world.isAirBlock(blockPos.add(i,j,k)) && (l < t / 2 || rand.nextBoolean()))
    					{
    						world.setBlockState(blockPos.add(i,j,k), Blocks.leaves.getStateFromMeta(4), 0);
    					}
    				}
    			}
    		}
    	}
    }
    
    private void createTrunk(World world, Random rand, BlockPos blockPos)
    {
    	int[] pos = new int[]{0,0, 1,0, 0,1, -1,0, 0,-1};
    	int sh;
    	for(int t = 0; t < 5; t++)
    	{    	
    		sh = rand.nextInt(3) + blockPos.getY();
    		while(sh > blockPos.getY() - 3)
    		{
    			if(world.getBlockState(blockPos.add(pos[t * 2], sh - blockPos.getY(), pos[t * 2 + 1])).getBlock() == Blocks.dirt)
    			{
    				break;
    			}
    			world.setBlockState(blockPos.add(pos[t * 2], sh - blockPos.getY(), pos[t * 2 + 1]), Blocks.log.getStateFromMeta(12), 0);
    			sh--;
    		}
    	}
    }
}
