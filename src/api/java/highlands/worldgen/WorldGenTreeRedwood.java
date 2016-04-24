package highlands.worldgen;

import highlands.Highlands;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeRedwood extends WorldGenHighlandsTreeBase
{

    /** Constructor - gets the generator for the correct highlands tree
     * @param lmd leaf meta data
     * @param wmd wood meta data
     * @param wb wood block
     * @param lb leaf block
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenTreeRedwood(int lmd, int wmd, Block wb, Block lb, int minH, int maxH, boolean notify)
    {
    	super(lmd, wmd, wb, lb, notify);
        
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeRedwood(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.redwoodWood, HighlandsBlocks.redwoodLeaves, minH, maxH, notify);
    	if(Highlands.vanillaBlocksFlag){
    		this.wood = Blocks.log;
    		this.leaves = Blocks.leaves;
    		this.leavesMeta = 1;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.worldObj = world;
    	this.random = random;
    	
        
        if(!isLegalTreePosition(world, locX, locY, locZ)) {
        	this.worldObj = null;
        	return false;
        }
        if(!isCubeClear(locX, locY+3, locZ, 4, 20)) {
        	this.worldObj = null;
        	return false;
        }
    	
        
        int treeHeight = minHeight + random.nextInt(maxHeight);
		//generates the trunk - different than other tree types, uses layer circles.
		double theta = Math.atan(2.8/treeHeight);
		
		for(int i = -3; i <= treeHeight; i++){
			double r = (treeHeight - i) * Math.tan(theta);
			generateWoodLayerCircle(world, random, r, locX, locZ, locY+i);
		}
		
		//generate the branches. They start halfway up the tree and are generated every 5 blocks up.
		int h = locY + treeHeight - 5;
		double r = 2;
		for(int i = 0; i < 6; i++){
			generateSequoiaBranch(world, random, r, locX, locZ, h);
			h-=3;
			if(r < 6)r++;
		}
		
		// very top of tree
		h = locY + treeHeight - 2;
		generateLeafLayerCircleNoise(world, random, 3.5, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(world, random, 2.5, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(world, random, 2, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(world, random, 1.5, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(world, random, 1, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(world, random, 0, locX, locZ, h);
		h++;
		generateLeafLayerCircleNoise(world, random, 0, locX, locZ, h);
		
		this.worldObj = null;
		return true;
    }
}













