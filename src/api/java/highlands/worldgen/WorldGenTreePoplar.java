package highlands.worldgen;

import highlands.Highlands;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreePoplar extends WorldGenHighlandsTreeBase
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
    public WorldGenTreePoplar(int lmd, int wmd, Block wb, Block lb, int minH, int maxH, boolean notify)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreePoplar(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.poplarWood, HighlandsBlocks.poplarLeaves, minH, maxH, notify);
    	if(Highlands.vanillaBlocksFlag){
    		this.wood = Blocks.log;
    		this.woodMeta = 2;
    		this.leaves = Blocks.leaves;
    		this.leavesMeta = 2;
    	}
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.worldObj = world;
    	this.random = random;
    	
    	
        
        if(!isLegalTreePosition(world, locX, locY, locZ))return false;
        if(!isCubeClear(locX, locY+3, locZ, 1, 8))return false;
    	
        //generates the trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.wood, this.woodMeta);
    	}
    	
    	//generates the leaves.
    	int h = locY + 3;
    	generateLeafLayerCircle(world, random, 1, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(world, random, 1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircle(world, random, 2, locX, locZ, h);
    	
    	for(h = h + 1; h < locY + treeHeight; h++){
    		generateLeafLayerCircleNoise(world, random, 2.8, locX, locZ, h);
    	}
    	generateLeafLayerCircleNoise(world, random, 2, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(world, random, 1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(world, random, 1, locX, locZ, h);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leaves, this.leavesMeta);
    	h++;
    	setBlockInWorld(locX, h, locZ, this.leaves, this.leavesMeta);
    	this.worldObj = null;
    	return true;
    }
    
}













