package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRedwood extends WorldGenerator
{
	private int height;
	private int leaves;
	private int trunk;
	private int metadata;
	
	public WorldGenTreeRedwood(int h, int l, int t, int m)
	{
		height = h;
		leaves = l;
		trunk = t;
		metadata = m;
	}

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt && g != Blocks.sand)
    	{
    		return false;
    	}
    	
    	for(int l1 = 0; l1 < 5; l1++)
    	{
			genLeaves(world, rand, x - 1 + rand.nextInt(3), y + height - l1, z - 1 + rand.nextInt(3), 1);
			genLeaves(world, rand, x - 1 + rand.nextInt(3), y + height - l1, z - 1 + rand.nextInt(3), 1);
    	}
    	for(int l2 = 5; l2 < leaves; l2++)
    	{
			genLeaves(world, rand, x - 2 + rand.nextInt(5), y + height - l2, z - 2 + rand.nextInt(5), 2);
			if(rand.nextBoolean())
			{
				genLeaves(world, rand, x - 2 + rand.nextInt(5), y + height - l2, z - 2 + rand.nextInt(5), 2);
			}
    	}
    	
    	for(int i = 0; i < height; i++)
    	{
        	world.setBlock(x, y + i, z, Blocks.log, 0, 0);
    	}
    	world.setBlock(x, y + height, z, Blocks.leaves, metadata, 0);
    	createTrunk(world, rand, x, y, z);
    	
    	return true;
    }

    public void genLeaves(World world, Random rand, int x, int y, int z, int size)
    {
    	int i;
    	int j;
    	int dis;
    	for(i = -1; i <= 1; i++)
    	{
    		for(j = -1; j <= 1; j++)
    		{
    			dis = Math.abs(i) + Math.abs(j);
    			if(world.isAirBlock(x + i, y + 1, z + j) && (dis < size - 1 || (dis < size && rand.nextBoolean())))
    			{
    				world.setBlock(x + i, y + 1, z + j, Blocks.leaves, metadata, 0);
    			}
    		}
    	}
    	
    	for(i = -2; i <= 2; i++)
    	{
    		for(j = -2; j <= 2; j++)
    		{
    			dis = Math.abs(i) + Math.abs(j);
    			if(world.isAirBlock(x + i, y, z + j) && (dis < size * 2 - 1 || (dis < size * 2 && rand.nextBoolean())))
    			{
    				world.setBlock(x + i, y, z + j, Blocks.leaves, metadata, 0);
    			}
    		}
    	}
    	
    	if(size > 1)
    	{
    		world.setBlock(x, y, z, Blocks.log, 12, 0);
    	}
    }
    
    private void createTrunk(World world, Random rand, int x, int y, int z)
    {
    	int[] pos = new int[]{0,0, 1,0, 0,1, -1,0, 0,-1, 1,1, 1,-1, -1,1, -1,-1};
    	int sh;
    	Block b;
    	for(int t = 0; t < 9; t++)
    	{    	
    		sh = pos[t*2] == 0 || pos[t*2+1] == 0 ? rand.nextInt(trunk * 2) + y + trunk : rand.nextInt(trunk) + y - 1;
    		while(sh > y - 2)
    		{
    			if(world.getBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1]) == Blocks.grass)
    			{
    				break;
    			}
    			world.setBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1], Blocks.log, 12, 0);
    			sh--;
    		}
    	}
    }
}
