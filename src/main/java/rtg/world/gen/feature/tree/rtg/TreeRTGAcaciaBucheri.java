package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.config.rtg.ConfigRTG;

public class TreeRTGAcaciaBucheri extends TreeRTG
{

    public TreeRTGAcaciaBucheri()
    {
    	super();
    	
    	this.logBlock = Blocks.log2;
    	this.logMeta = (byte)0;
    	this.leavesBlock = Blocks.leaves2;
    	this.leavesMeta = (byte)0;
		this.trunkSize = 10;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block b = world.getBlock(x, y - 1, z);
    	
        if (b == Blocks.sand && !ConfigRTG.allowTreesToGenerateOnSand) {
            return false;
        }
    	
    	if(b != Blocks.grass && b != Blocks.dirt)
    	{
    		return false;
    	}

    	int h = this.trunkSize;
    	int bh = h - 3;
    	
    	for(int i = 0; i < h; i++)
    	{
    		world.setBlock(x, y + i, z, this.logBlock, this.logMeta, 0);
    	}
		genLeaves(world, rand, x, y + h, z);
		
		int sh, eh, dir;
		float xd, yd, c;
		
		for(int a = 1 + rand.nextInt(2); a > -1; a--)
		{
			sh = bh + rand.nextInt(2);
			eh = h - (int)((h - sh) * 0.25f);
			dir = rand.nextInt(360);
			xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
			yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
			c = 1;
			
			while(sh < h)
			{
				world.setBlock(x + (int)(xd * c), y + sh, z + (int)(yd * c), this.logBlock, this.logMeta, 0);
				sh++;
				c += 0.5f;
			}
			genLeaves(world, rand, x + (int)(xd * c), y + sh, z + (int)(yd * c));
		}
    	
    	return true;
    }
    
    public void genLeaves(World world, Random rand, int x, int y, int z)
    {
    	int i;
    	int j;
    	for(i = -1; i <= 1; i++)
    	{
    		for(j = -1; j <= 1; j++)
    		{
    			if(world.isAirBlock(x + i, y + 1, z + j))
    			{
    				world.setBlock(x + i, y + 1, z + j, this.leavesBlock, this.leavesMeta, 0);
    			}
    		}
    	}
    	
    	for(i = -2; i <= 2; i++)
    	{
    		for(j = -2; j <= 2; j++)
    		{
    			if(world.isAirBlock(x + i, y, z + j) && Math.abs(i) + Math.abs(j) < 4)
    			{
    				world.setBlock(x + i, y, z + j, this.leavesBlock, this.leavesMeta, 0);
    			}
    		}
    	}
    	
    	world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    }
}
