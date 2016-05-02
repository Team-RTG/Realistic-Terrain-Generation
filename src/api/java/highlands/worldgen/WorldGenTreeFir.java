package highlands.worldgen;

import highlands.Highlands;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeFir extends WorldGenHighlandsTreeBase
{
    private boolean trunk2;

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
    public WorldGenTreeFir(int lmd, int wmd, Block wb, Block lb, int minH, int maxH, boolean notify, boolean thickTrunk)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
        this.trunk2 = thickTrunk;
    }
    
    public WorldGenTreeFir(int minH, int maxH, boolean notify, boolean thickTrunk){
    	this(0, 0, HighlandsBlocks.firWood, HighlandsBlocks.firLeaves, minH, maxH, notify, thickTrunk);
    	if(Highlands.vanillaBlocksFlag){
    		this.wood = Blocks.log;
    		this.woodMeta = 1;
    		this.leaves = Blocks.leaves;
    		this.leavesMeta = 1;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.worldObj = world;
    	this.random = random;
    	
    	boolean isWide = (random.nextInt(3) == 0);
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	
        
        if(!isLegalTreePosition(world, locX, locY, locZ) 
        		&& world.getBlock(locX, locY-1, locZ) != Blocks.snow)return false;
        if(!isCubeClear(locX, locY+3, locZ, 2, 10))return false;
    	
		//generates the trunk
    	genTree(world, random, locX, locY, locZ, treeHeight, isWide);
    	
    	
    	
    	if(this.trunk2){
    		genTree(world, random, locX+1, locY, locZ, treeHeight, isWide);
    		genTree(world, random, locX, locY, locZ+1, treeHeight, isWide);
    		genTree(world, random, locX+1, locY, locZ+1, treeHeight, isWide);
    	}
    	this.worldObj = null;
    	return true;
    }
    
    
    //TREE GENERATORS
    
	private boolean genTree(World world, Random random, int locX, int locY, int locZ, int treeHeight, boolean isWide){
    	//generates the trunk
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.wood, this.woodMeta);
    	}
    	
    	//generates the leaves
    	double leafRadius = 4.5;
    	double finalRadius = 2.5;
    	
    	int h0 = 6;
    	if(treeHeight < 16) h0 = 3;
    	int h;
    	for(h = locY + h0; h < treeHeight + locY; h++){
    		//level 1
    		if(isWide){
    			generateLeafLayerCircleNoise(world, random, leafRadius + 1, locX, locZ, h);
    			h++;
    		}
    		//level 2
    		generateLeafLayerCircleNoise(world, random, leafRadius, locX, locZ, h);
    		h++;
    		//level 3
    		generateLeafLayerCircleNoise(world, random, leafRadius - 1, locX, locZ, h);
    		//if not wide, gen a smaller layer
    		if(!isWide){
    			h++;
    			generateLeafLayerCircleNoise(world, random, leafRadius - 1.5, locX, locZ, h);
    		}
    		
    		leafRadius -= (leafRadius - finalRadius)/3.0;
    		
    	}
    	//generate top of tree
    	if(isWide){
    		generateLeafLayerCircleNoise(world, random, 2, locX, locZ, h);
    		h++;
    	}
    	generateLeafLayerCircleNoise(world, random, 1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(world, random, 1, locX, locZ, h);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leaves, this.leavesMeta);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leaves, this.leavesMeta);
    	return true;
    }
    
}













