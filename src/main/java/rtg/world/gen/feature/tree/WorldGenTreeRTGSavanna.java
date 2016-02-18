package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.util.BlockPos;
import rtg.config.rtg.ConfigRTG;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGSavanna extends WorldGenerator
{
	private int type;
	private boolean sand;
	
    public WorldGenTreeRTGSavanna(int t)
    {
    	this(t, true);
    }
    
    public WorldGenTreeRTGSavanna(int t, boolean s)
    {
    	type = t;
    	sand = s;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
    	Block b = world.getBlockState(blockPos.down()).getBlock();
    	
        if (b == Blocks.sand && !ConfigRTG.allowTreesToGenerateOnSand) {
            return false;
        }
    	
    	if(b != Blocks.grass && b != Blocks.dirt && ((sand && b != Blocks.sand) || !sand))
    	{
    		return false;
    	}
    	
    	if(type == 0)
    	{
	    	int h = 10 + rand.nextInt(5);
	    	int bh = h - 6;
	    	
	    	for(int i = 0; i < h; i++)
	    	{
	    		world.setBlockState(blockPos.up(), Blocks.log2.getStateFromMeta(0), 0);
	    	}
			genLeaves(world, rand, blockPos.up(h));
			
			int sh, eh, dir;
			float xd, yd, c;
			
			for(int a = 7 - 1 + rand.nextInt(3); a > -1; a--)
			{
				sh = bh + rand.nextInt(6 - 2);
				eh = h - (int)((h - sh) * 1f);
				dir = rand.nextInt(360);
				xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
				yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
				c = 1;
				
				while(sh < h)
				{
					world.setBlockState(blockPos.add((int)(xd * c), sh, (int)(yd * c)), Blocks.log2.getStateFromMeta(0), 0);
					sh++;
					c += 0.5f;
				}
				genLeaves(world, rand, blockPos.add( (int)(xd * c), sh, (int)(yd * c)));
			}
    	}
    	else if(type == 1)
    	{
	    	int h = 6 + rand.nextInt(3);
	    	int bh = h - 3;
	    	
	    	for(int i = 0; i < h; i++)
	    	{
	    		world.setBlockState(blockPos.up(), Blocks.log2.getStateFromMeta(0), 0);
	    	}
			genLeaves(world, rand, blockPos.up(h));
			
			int sh, eh, dir;
			float xd, yd, c;
			
			for(int a = 1 + rand.nextInt(2); a > -1; a--)
			{
				sh = bh + rand.nextInt(3 - 1);
				eh = h - (int)((h - sh) * 0.25f);
				dir = rand.nextInt(360);
				xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
				yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
				c = 1;
				
				while(sh < h)
				{
					world.setBlockState(blockPos.add((int)(xd * c), sh, (int)(yd * c)), Blocks.log2.getStateFromMeta(0), 0);
					sh++;
					c += 0.5f;
				}
				genLeaves(world, rand, blockPos.add( (int)(xd * c), sh, (int)(yd * c)));
			}
    	}
    	else if(type == 2)
    	{
	    	int h = 12 + rand.nextInt(5);
	    	int bh = h - 3;
	    	
	    	for(int i = 0; i < h; i++)
	    	{
	    		world.setBlockState(blockPos.up(), Blocks.log2.getStateFromMeta(0));
	    	}
			genLeaves(world, rand, blockPos.up(h));
			
			int sh, eh, dir;
			float xd, yd, c;
			
			for(int a = 1 + rand.nextInt(2); a > -1; a--)
			{
				sh = bh + rand.nextInt(3 - 1);
				eh = h - (int)((h - sh) * 0.25f);
				dir = rand.nextInt(360);
				xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
				yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
				c = 1;
				
				while(sh < h)
				{
					world.setBlockState(blockPos.add((int)(xd * c), sh, (int)(yd * c)), Blocks.log2.getStateFromMeta(0));
					sh++;
					c += 0.5f;
				}
				genLeaves(world, rand, blockPos.add( (int)(xd * c), sh, (int)(yd * c)));
			}
    	}
    	
    	return true;
    }
    
    public void genLeaves(World world, Random rand, BlockPos blockPos)
    {
    	if(type == 0)
    	{
	    	int i;
	    	int j;
	    	for(i = -2; i <= 2; i++)
	    	{
	    		for(j = -2; j <= 2; j++)
	    		{
	    			if(world.isAirBlock(blockPos.add(i,1,j)) && Math.abs(i) + Math.abs(j) < 4)
	    			{
	    				world.setBlockState(blockPos.add(i,1,j), Blocks.leaves2.getDefaultState(), 0);
	    			}
	    		}
	    	}
	    	
	    	for(i = -3; i <= 3; i++)
	    	{
	    		for(j = -3; j <= 3; j++)
	    		{
	    			if(world.isAirBlock(blockPos.add(i, 0, j)) && Math.abs(i) + Math.abs(j) < 5)
	    			{
	    				world.setBlockState(blockPos.add(i, 0, j), Blocks.leaves2.getDefaultState(), 0);
	    			}
	    		}
	    	}
	    	
	    	world.setBlockState(blockPos, Blocks.log2.getDefaultState());
    	}
    	else
    	{
	    	int i;
	    	int j;
	    	for(i = -1; i <= 1; i++)
	    	{
	    		for(j = -1; j <= 1; j++)
	    		{
	    			if(world.isAirBlock(blockPos.add(i,1,j)))
	    			{
	    				world.setBlockState(blockPos.add(i,1,j), Blocks.leaves2.getDefaultState(), 0);
	    			}
	    		}
	    	}
	    	
	    	for(i = -2; i <= 2; i++)
	    	{
	    		for(j = -2; j <= 2; j++)
	    		{
	    			if(world.isAirBlock(blockPos.add(i, 0, j)) && Math.abs(i) + Math.abs(j) < 4)
	    			{
	    				world.setBlockState(blockPos.add(i, 0, j), Blocks.leaves2.getDefaultState(), 0);
	    			}
	    		}
	    	}
	    	
	    	world.setBlockState(blockPos, Blocks.log2.getDefaultState());
    	}
    }
}
