package highlands.worldgen;

import highlands.Highlands;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeDiamondheart extends WorldGenHighlandsTreeBase
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
    public WorldGenTreeDiamondheart(int lmd, int wmd, Block wb, Block lb, int minH, int maxH, boolean notify)
    {
    	super(lmd, wmd, wb, lb, notify);
        
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeDiamondheart(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.ironWood, HighlandsBlocks.acaciaLeaves, minH, maxH, notify);
    	if(Highlands.vanillaBlocksFlag){
    		this.wood = Blocks.log;
    		this.leaves = Blocks.leaves;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.worldObj = world;
    	this.random = random;
    	

        
        if(!isLegalTreePosition(world, locX, locY, locZ))return false;
        if(!isCubeClear(locX, locY+3, locZ, 8, 60))return false;
        
    	//generates the trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	double primaryRadius = 16;
    	double finalRadius = 5;
    	double secondaryRadius = 3.5;
    	double finalRadius2 = 2;
    	double angleRad = 0;
    	
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.wood, this.woodMeta);
    		for(double j = angleRad; j < angleRad + 2*Math.PI; j += Math.PI/4){
    			int x = (int)(primaryRadius * Math.cos(j));
    			int z = (int)(primaryRadius * Math.sin(j));
    			generateWoodLayerCircle(world, random, secondaryRadius, x+locX, z+locZ, i+locY);
    		}
    		angleRad += Math.PI / 40;
    		primaryRadius -= (primaryRadius - finalRadius)/15.0;
    		secondaryRadius -= (secondaryRadius - finalRadius2)/15.0;
    	}
    	
    	/*
    	int h = locY + treeHeight - 1;
    	//generate leaves above trunk
    	generateLeafLayerCircle(world, random, 5.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(world, random, 4.5, locX, locZ, h);

		h = locY + treeHeight - 6;
		//generates branch
		int[] xyz = generateStraightBranch(world, random, 4, locX, h, locZ, random.nextInt(4));
		generateLeafLayerCircle(world, random, 4.5, xyz[0], xyz[2], xyz[1]);
		generateLeafLayerCircle(world, random, 3.5, xyz[0], xyz[2], xyz[1]+1);
		*/
    	this.worldObj = null;
		return true;
    }
}













