package highlands.worldgen;

import highlands.block.BlockHighlandsSapling;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenHighlandsTreeBase extends WorldGenAbstractTree
{
	protected Block wood;
	protected Block leaves;
    protected int woodMeta;
    protected int leavesMeta;
    protected int type;
    
    protected int minHeight;
    protected int maxHeight;
    
    protected boolean notifyFlag;
    
    protected World worldObj;
    protected Random random;
    
    //this array is the 8 directions of x and y, used for palm trees.
    protected int[][]directions = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};

    /** Constructor - sets up tree variables
     * @param lmd leaf meta data
     * @param wmd wood meta data
     * @param log wood block
     * @param leaves leaf block
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenHighlandsTreeBase(int lmd, int wmd, Block log, Block leaves, boolean notify)
    {
    	super(true);
        this.wood = log;
        this.leaves = leaves;
        this.woodMeta = wmd;
        this.leavesMeta = lmd;
        this.notifyFlag = notify;
    }

    public abstract boolean generate(World world, Random random, int locX, int locY, int locZ);

    
	//UTILITY GENERATORS - LEAVES, BRANCHES, TRUNKS
    
    //is the position of the tree dirt or grass?
    public boolean isLegalTreePosition(World world, int x, int y, int z){
    	//System.out.println("Tree Position " + x + " " + y + " "+ z +" is " + (world.getBlockId(x, y-1, z) == Block.grass.blockID ||
    	//		world.getBlockId(x, y-1, z) == Block.dirt.blockID));
    	return (world.getBlock(x, y-1, z) == Blocks.grass ||
    			world.getBlock(x, y-1, z) == Blocks.dirt);
    }
    
    public boolean generateReplaceSapling(World world, Random random, int locX, int locY, int locZ){
    	Block id = world.getBlock(locX, locY, locZ);
    	int meta = world.getBlockMetadata(locX, locY, locZ);
    	boolean flag = generate(world, random, locX, locY, locZ);
    	if(!flag) 
    		world.setBlock(locX, locY, locZ, id, meta, 3);
    	return flag;
    }
    
    
    //generates a circular disk of leaves around a coordinate block, only overwriting air blocks.
    protected void generateLeafLayerCircle(World world, Random random, double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					setBlockInWorld(x, h, z, this.leaves, this.leavesMeta);
				}
			}
		}
    }
    
    //generates a circular disk of leaves around a coordinate block, only overwriting air blocks.
    //noise means the outer block has a 50% chance of generating
    protected void generateLeafLayerCircleNoise(World world, Random random, double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					if(xfr * xfr + zfr * zfr <= (radius - 1) * (radius - 1) || random.nextInt(2) == 0){
						setBlockInWorld(x, h, z, this.leaves, this.leavesMeta);
					}
				}
			}
		}
    }
    
    //generates a circular disk of wood around a coordinate block, only overwriting air and leaf blocks.
    protected void generateWoodLayerCircle(World world, Random random, double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					setBlockInWorld(x, h, z, this.wood, this.woodMeta);
				}
			}
		}
    }
    
    //generate a branch, can be any direction
    //startHeight is absolute, not relative to the tree.
    //dir = direction: 0 = north (+z) 1 = east (+x) 2 = south 3 = west
    protected int[] generateStraightBranch(World world, Random random, int length, int locX, int locY, int locZ, int dir){
    	int direction = -1;
    	if(dir < 2)
    		 direction = 1;
    	if(dir % 2 == 0){
    		//generates branch
    		for(int i = 1; i <= length; i++){
	    		setBlockInWorld(locX + i*direction, locY+i, locZ, this.wood, this.woodMeta+4);
    		}
    		return new int[]{locX+length*direction, locY+length, locZ};
    	}
    	else{
    		for(int i = 1; i <= length; i++){
	    		setBlockInWorld(locX, locY+i, locZ + i*direction, this.wood, this.woodMeta+8);
    		}
    		return new int[]{locX, locY+length, locZ+length*direction};
    	}
    }
    
    //same as GenerateStraightBranch but downward
    protected int[] generateStraightBranchDown(World world, Random random, int length, int locX, int locY, int locZ, int dir){
    	int direction = -1;
    	if(dir < 2)
    		 direction = 1;
    	if(dir % 2 == 0){
    		//generates branch
    		for(int i = 1; i <= length; i++){
    			setBlockInWorld(locX + i*direction, locY+i, locZ, this.wood, this.woodMeta+4);
    		}
    		return new int[]{locX+length*direction, locY-length, locZ};
    	}
    	else{
    		for(int i = 1; i <= length; i++){
    			setBlockInWorld(locX, locY+i, locZ + i*direction, this.wood, this.woodMeta+8);
    		}
    		return new int[]{locX, locY-length, locZ+length*direction};
    	}
    }

	protected void generateSequoiaBranch(World world, Random random, double length, int xo, int zo, int h){
		for(int i = 0; i < length; i++){
			int j = i - 3;
			//east
			setBlockInWorld(xo+i, h, zo, this.wood, this.woodMeta+4);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockInWorld(xo+i, h, zo+j, this.wood, this.woodMeta+8);
				setBlockInWorld(xo+i, h, zo-j, this.wood, this.woodMeta+8);
			}
			//north
			setBlockInWorld(xo, h, zo+i, this.wood, this.woodMeta+8);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockInWorld(xo+j, h, zo+i, this.wood, this.woodMeta+4);
				setBlockInWorld(xo-j, h, zo+i, this.wood, this.woodMeta+4);
			}
			//west
			setBlockInWorld(xo-i, h, zo, this.wood, this.woodMeta+4);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockInWorld(xo-i, h, zo+j, this.wood, this.woodMeta+8);
				setBlockInWorld(xo-i, h, zo-j, this.wood, this.woodMeta+8);
			}
			//south
			setBlockInWorld(xo, h, zo-i, this.wood, this.woodMeta+8);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockInWorld(xo+j, h, zo-i, this.wood, this.woodMeta+4);
				setBlockInWorld(xo-j, h, zo-i, this.wood, this.woodMeta+4);
			}
			
			//generate leaves for branches
			if(i == length - 2){
				double lr = 3.5;
				//if(length > 6)lr++;
				generateLeafLayerCircleNoise(world, random, lr, xo+i, zo, h+1);
				generateLeafLayerCircleNoise(world, random, lr, xo-i, zo, h+1);
				generateLeafLayerCircleNoise(world, random, lr, xo, zo+i, h+1);
				generateLeafLayerCircleNoise(world, random, lr, xo, zo-i, h+1);
				lr--;
				generateLeafLayerCircleNoise(world, random, lr, xo+i, zo, h+2);
				generateLeafLayerCircleNoise(world, random, lr, xo-i, zo, h+2);
				generateLeafLayerCircleNoise(world, random, lr, xo, zo+i, h+2);
				generateLeafLayerCircleNoise(world, random, lr, xo, zo-i, h+2);
			}
		}
		/*
		if(length > 6){
    		for(int i = 1; i < length / (2 * Math.sqrt(2)) ; i++){
    			//setBlockInWorld(xo, h, z, this.woodID, this.woodMeta);
    		}
		}
		*/
    	
    }
    
    
    protected void setBlockInWorld(int x, int y, int z, Block block, int meta){
    	try{
    		Block bl = worldObj.getBlock(x, y, z);
    		//TODO- right fix? might be crashy...
			//if(block == this.wood && (world.isAirBlock(x,y,z) || bl.isReplaceable(world, x, y, z) ||
			//		bl.isLeaves(world, x, y, z) || bl instanceof BlockHighlandsSapling)){
            if (bl.isFoliage(worldObj, x, y, z) || worldObj.isAirBlock(x, y, z) || bl instanceof BlockLiquid ||
            		bl instanceof BlockHighlandsSapling ||
            		bl.getMaterial() == Material.plants || bl.getMaterial() == Material.vine) {
				if(notifyFlag) worldObj.setBlock(x, y, z, block, meta, 3);
		    	else worldObj.setBlock(x, y, z, block, meta, 2);
			}
			//else if(block == this.leaves && world.isAirBlock(x,y,z)){
			//	if(notifyFlag) world.setBlock(x, y, z, block, meta, 3);
		    //	else world.setBlock(x, y, z, block, meta, 2);
			//}
    	}
    	catch(RuntimeException e){
    		if(e.getMessage().equals("Already decorating!!")){
    			System.out.println("Error: Tree block couldn't generate!");
    		}
    		//e.printStackTrace();
    	}
    }
    
    
    
    protected boolean isCubeClear (int x, int y, int z, int radius, int height){
    	for(int i = x-radius; i <= x+radius; i++){
    		for(int j = z-radius; j <= z+radius; j++){
    			for(int k = y; k <= y+height; k++){
    				if(!(worldObj.isAirBlock(i, k, j) || worldObj.getBlock(i, k, j).isLeaves(worldObj, i, k, j)))return false;
    			}
    		}
    	}
    	return true;
    }
    
    //finds top block for the given x,z position (excluding leaves)
    protected int findTopBlock(int x, int z){
    	int y = 256;
        for (boolean var6 = false; (worldObj.isAirBlock(x, y, z) || worldObj.getBlock(x, y, z).isLeaves(worldObj, x, y, z)) && y > 0; --y);
        return y;
    }
}













