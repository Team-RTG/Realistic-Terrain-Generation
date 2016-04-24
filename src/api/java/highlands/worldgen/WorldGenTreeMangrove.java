package highlands.worldgen;

import highlands.Highlands;
import highlands.api.HighlandsBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTreeMangrove extends WorldGenHighlandsTreeBase
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
    public WorldGenTreeMangrove(int lmd, int wmd, Block wb, Block lb, int minH, int maxH, boolean notify)
    {
        super(lmd, wmd, wb, lb, notify);
        this.minHeight = minH;
        this.maxHeight = maxH;
    }
    
    public WorldGenTreeMangrove(int minH, int maxH, boolean notify){
    	this(0, 0, HighlandsBlocks.mangroveWood, HighlandsBlocks.mangroveLeaves, minH, maxH, notify);
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
    	
    	if(locY < 62 && !notifyFlag) locY = 62;
        
        if(!(world.getBlock(locX, locY-1, locZ) == Blocks.grass 
        		|| world.getBlock(locX, locY-1, locZ) == Blocks.dirt
                || world.getBlock(locX, locY-1, locZ) == Blocks.sand 
                || world.getBlock(locX, locY-1, locZ) == Blocks.water))return false;
        if(!isCubeClear(locX, locY+2, locZ, 0, 3))return false;
    	
        
        int waterH = 0;//height of water
        for (boolean var6 = false; (world.isAirBlock(locX, locY-waterH, locZ) || world.getBlock(locX, locY-waterH, locZ) == Blocks.leaves 
        		|| world.getBlock(locX, locY-waterH, locZ) == Blocks.water) && locY-waterH > 0; ++waterH);
        if(waterH > 4)return false;
        
        
    	//generates trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight) - 2;
    	locY++;
    	for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.wood, this.woodMeta);
    	}
    	//generates roots
    	for(int i = -1; i > -7; i--){
    		setBlockInWorld(locX + i, locY + i, locZ, this.wood, this.woodMeta);
    		setBlockInWorld(locX - i, locY + i, locZ, this.wood, this.woodMeta);
    		setBlockInWorld(locX, locY + i, locZ + i, this.wood, this.woodMeta);
    		setBlockInWorld(locX, locY + i, locZ - i, this.wood, this.woodMeta);
    	}
		//generates leaves
		int h = locY + treeHeight - 1;
		generateLeafLayerCircle(world, random, 3.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(world, random, 2.5, locX, locZ, h);
		h++;
		generateLeafLayerCircle(world, random, 1, locX, locZ, h);
		this.worldObj = null;
    	return true;
    }
    
}













