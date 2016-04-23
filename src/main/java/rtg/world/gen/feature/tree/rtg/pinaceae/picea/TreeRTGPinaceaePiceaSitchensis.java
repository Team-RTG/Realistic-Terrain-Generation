package rtg.world.gen.feature.tree.rtg.pinaceae.picea;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.world.gen.feature.tree.rtg.pinaceae.TreeRTGPinaceaePicea;

public class TreeRTGPinaceaePiceaSitchensis extends TreeRTGPinaceaePicea
{

	public TreeRTGPinaceaePiceaSitchensis()
	{
		super();
	}

	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}

    	int i;
    	for(i = 0; i < this.trunkSize; i++)
    	{
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    		y++;
    	}

    	int pX = 0;
    	int pZ = 0;
    	for(i = 0; i < this.crownSize; i++)
    	{
    		if(rand.nextInt(2) == 0 && i < this.crownSize - 2)
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

        		buildBranch(world, rand, x, y, z, dX, dZ, i < this.crownSize - 10 ? 2 : 1, i < this.crownSize - 6 ? 2 : 1);
    		}
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);

    		if(i < this.crownSize - 2)
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
