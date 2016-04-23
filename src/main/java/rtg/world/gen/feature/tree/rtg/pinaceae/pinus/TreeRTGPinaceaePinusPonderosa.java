package rtg.world.gen.feature.tree.rtg.pinaceae.pinus;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.world.gen.feature.tree.rtg.pinaceae.TreeRTGPinaceaePinus;

public class TreeRTGPinaceaePinusPonderosa extends TreeRTGPinaceaePinus
{

	public TreeRTGPinaceaePinusPonderosa()
	{
		super();
	}
	
	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	int startY = y;
    	
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	buildTrunk(world, rand, x + 1, y, z);
    	buildTrunk(world, rand, x - 1, y, z);
    	buildTrunk(world, rand, x, y, z + 1);
    	buildTrunk(world, rand, x, y, z - 1);
    	
    	int i;
    	for(i = 0; i < trunkSize; i++)
    	{
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    		if(i > 5 && rand.nextInt(7) == 0)
    		{
    			int dX = -1 + rand.nextInt(3);
    			int dZ = -1 + rand.nextInt(3);
    			
    			if(dX == 0 && dZ == 0)
    			{
    				dX = -1 + rand.nextInt(3);
    				dZ = -1 + rand.nextInt(3);
    			}
    			
    			buildBranch(world, rand, x, y, z, dX, dZ, 1, 1);
    		}
    		
    		y++;
    	}
    	
    	int pX = 0;
    	int pZ = 0;
    	int j;
    	for(i = 0; i < crownSize; i++)
    	{
    		if(rand.nextInt(i < crownSize - 12 && i > 2 ? 2 : 1) == 0 && i < crownSize - 2)
    		{
    			int dX = -1 + rand.nextInt(3);
    			int dZ = -1 + rand.nextInt(3);
    			
    			if(dX == 0 && dZ == 0)
    			{
    				dX = -1 + rand.nextInt(3);
    				dZ = -1 + rand.nextInt(3);
    			}
    			
    			if(pX == dX && rand.nextBoolean())
    			{
    				dX = -dX;
    			}
    			if(pZ == dZ && rand.nextBoolean())
    			{
    				dZ = -dZ;
    			}
    			
    			pX = dX;
    			pZ = dZ;

        		buildBranch(world, rand, x, y, z, dX, dZ, 
        			i < crownSize - 12 && i > 3 ? 3 : i < crownSize - 8 ? 2 : 1, 
        			i < crownSize - 5 ? 2 : 1
        		);
    		}
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    		
    		if(i < crownSize - 2)
	    	{
	    		if(rand.nextBoolean()) { buildLeaves(world, x, y, z + 1); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x, y, z - 1); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x + 1, y, z); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x - 1, y, z); }
    		}
    		
    		y++;
    	}
    	
    	buildLeaves(world, x, y - 1, z + 1);
    	buildLeaves(world, x, y - 1, z - 1);
    	buildLeaves(world, x + 1, y - 1, z);
    	buildLeaves(world, x - 1, y - 1, z);
    	buildLeaves(world, x, y, z);
    	
    	return true;
    }
}
