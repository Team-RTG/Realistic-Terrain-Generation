package highlands.worldgen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenShrubbery extends WorldGenHighlandsTreeBase
{

    /** Constructor
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenShrubbery(boolean notify)
    {
    	super(0, 0, Blocks.fence, Blocks.leaves, notify);
    }

    //a Shrubbery is a small tree with a 1 block high fence trunk and 2-4 blocks of leaves.
    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.worldObj = world;
    	this.random = random;
    	

    	//finds top block for the given x,z position (excluding leaves and grass)
        for (boolean var6 = false; (worldObj.isAirBlock(locX, locY, locZ) || world.getBlock(locX, locY, locZ) == Blocks.leaves) && locY > 0; --locY);
        //locY is now the highest solid terrain block
        
        if(!(world.getBlock(locX, locY, locZ) == Blocks.grass || world.getBlock(locX, locY, locZ) == Blocks.dirt))
        	return false;
        
    	//generates the trunk
    	locY++;
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	setBlockInWorld(locX, locY, locZ, this.wood, this.woodMeta);
    	
    	//generate leaves above trunk
    	int leafHeight = random.nextInt(3)+ 2;
    	for(int i = 1; i < leafHeight; i++){
    		setBlockInWorld(locX, locY + 1, locZ, this.leaves, this.leavesMeta);
    	}
    	
    	this.worldObj = null;
		return true;
    }
}













