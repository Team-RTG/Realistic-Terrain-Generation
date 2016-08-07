package com.sdj64.highlands.generator;

import java.util.Random;

import com.sdj64.highlands.block.BlockHighlandsLeaves;
import com.sdj64.highlands.block.BlockHighlandsSapling;
import com.sdj64.highlands.block.HighlandsBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;

public abstract class WorldGenMTreeBase extends WorldGenAbstractTree
{
	protected Block wood;
	protected Block leaves;
	protected int leafMeta;
	protected int woodMeta;
	
    protected int minHeight;
    protected int maxHeight;
    
    protected boolean notifyFlag;
    
    protected World world;
    protected Random random;

    
    /**
     * @param leafBlock Blocks.leaves = oak, spruce, birch, jungle.  Blocks.leaves2 = acacia, dark oak
     * @param woodBlock Blocks.log = oak, spruce, birch, jungle.  Blocks.log2 = acacia, dark oak
     * @param leafBlockMeta 0 = oak, acacia, 1 = spruce, dark oak, 2 = birch, 3 = jungle
     * @param woodBlockMeta 0 = oak, acacia, 1 = spruce, dark oak, 2 = birch, 3 = jungle
     * @param minH Minimum height of the tree trunk
     * @param maxH Maximum height of the tree trunk
     * @param notify False for world generation, true for saplings
     */
    public WorldGenMTreeBase(Block leafBlock, Block woodBlock, int leafBlockMeta, int woodBlockMeta, int minH, int maxH, boolean notify)
    {
    	super(notify);
        leaves = leafBlock;
        wood = woodBlock;
        woodMeta = woodBlockMeta;
        leafMeta = leafBlockMeta;
        minHeight = minH;
        maxHeight = maxH;
        notifyFlag = notify;
    }

    public abstract boolean generate(World wor, Random rand, BlockPos pos);

    
	//UTILITY GENERATORS - LEAVES, BRANCHES, TRUNKS
    
    //is the position of the tree dirt or grass?
    public boolean isLegalTreePosition(BlockPos pos, boolean snow, boolean sand){
    	
    	if(world.getBlockState(pos).getBlock().equals(Blocks.water))return false;
    	
    	pos = pos.down();
    	
    	//System.out.println("Tree Generating Block: " + world.getBlockState(pos).getBlock().getUnlocalizedName());
    	
    	
    	return (world.getBlockState(pos).getBlock().canSustainPlant((IBlockAccess)world, pos, EnumFacing.UP, (IPlantable)Blocks.sapling) ||
    			(world.getBlockState(pos).getBlock().equals(Blocks.sand) && sand) ||
    			(world.getBlockState(pos).getBlock().equals(Blocks.snow) && snow)
    			);
    }
    
    
    //generates a circular disk of leaves around a coordinate block, only overwriting air blocks.
    protected void generateLeafLayerCircle(double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					setBlockLeaf(new BlockPos(x, h, z));
				}
			}
		}
    }
    
    //generates a circular disk of leaves around a coordinate block
    //noise means the outer block has a 50% chance of generating
    protected void generateLeafLayerCircleNoise(double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					if(xfr * xfr + zfr * zfr <= (radius - 1) * (radius - 1) || random.nextInt(2) == 0){
						setBlockLeaf(new BlockPos(x, h, z));
					}
				}
			}
		}
    }
    
    //generates a circular disk of wood around a coordinate block
    protected void generateWoodLayerCircle(double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					setBlockLog(new BlockPos(x, h, z), 0);
				}
			}
		}
    }
    
    //generate a branch, can be any direction
    //startHeight is absolute, not relative to the tree.
    //dir = direction: 0 = north (+z) 1 = east (+x) 2 = south 3 = west
    protected BlockPos generateStraightBranch(int length, int locX, int locY, int locZ, int dir){
    	int direction = -1;
    	if(dir < 2)
    		 direction = 1;
    	if(dir % 2 == 0){
    		//generates branch
    		for(int i = 1; i <= length; i++){
	    		setBlockLog(new BlockPos(locX + i*direction, locY+i, locZ), 4);
    		}
    		return new BlockPos(locX+length*direction, locY+length, locZ);
    	}
    	else{
    		for(int i = 1; i <= length; i++){
	    		setBlockLog(new BlockPos(locX, locY+i, locZ + i*direction), 8);
    		}
    		return new BlockPos(locX, locY+length, locZ+length*direction);
    	}
    }
    
    //same as GenerateStraightBranch but downward
    protected int[] generateStraightBranchDown(int length, int locX, int locY, int locZ, int dir){
    	int direction = -1;
    	if(dir < 2)
    		 direction = 1;
    	if(dir % 2 == 0){
    		//generates branch
    		for(int i = 1; i <= length; i++){
    			setBlockLog(new BlockPos(locX + i*direction, locY+i, locZ), 4);
    		}
    		return new int[]{locX+length*direction, locY-length, locZ};
    	}
    	else{
    		for(int i = 1; i <= length; i++){
    			setBlockLog(new BlockPos(locX, locY+i, locZ + i*direction), 8);
    		}
    		return new int[]{locX, locY-length, locZ+length*direction};
    	}
    }

	protected void generateSequoiaBranch(double length, int xo, int zo, int h){
		for(int i = 0; i < length; i++){
			int j = i - 3;
			//east
			setBlockLog(new BlockPos(xo+i, h, zo), 4);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockLog(new BlockPos(xo+i, h, zo+j), 8);
				setBlockLog(new BlockPos(xo+i, h, zo-j), 8);
			}
			//north
			setBlockLog(new BlockPos(xo, h, zo+i), 8);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockLog(new BlockPos(xo+j, h, zo+i), 4);
				setBlockLog(new BlockPos(xo-j, h, zo+i), 4);
			}
			//west
			setBlockLog(new BlockPos(xo-i, h, zo), 4);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockLog(new BlockPos(xo-i, h, zo+j), 8);
				setBlockLog(new BlockPos(xo-i, h, zo-j), 8);
			}
			//south
			setBlockLog(new BlockPos(xo, h, zo-i), 8);
			if(length > 3 && j > 0 && i < length - 1){
				setBlockLog(new BlockPos(xo+j, h, zo-i), 4);
				setBlockLog(new BlockPos(xo-j, h, zo-i), 4);
			}
			
			//generate leaves for branches
			if(i == length - 2){
				double lr = 3.5;
				//if(length > 6)lr++;
				generateLeafLayerCircleNoise(lr, xo+i, zo, h+1);
				generateLeafLayerCircleNoise(lr, xo-i, zo, h+1);
				generateLeafLayerCircleNoise(lr, xo, zo+i, h+1);
				generateLeafLayerCircleNoise(lr, xo, zo-i, h+1);
				lr--;
				generateLeafLayerCircleNoise(lr, xo+i, zo, h+2);
				generateLeafLayerCircleNoise(lr, xo-i, zo, h+2);
				generateLeafLayerCircleNoise(lr, xo, zo+i, h+2);
				generateLeafLayerCircleNoise(lr, xo, zo-i, h+2);
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
    
    
    protected void setBlockLeaf(BlockPos pos){
    	try{
			if(world.isAirBlock(pos) || world.getBlockState(pos).getBlock().equals(Blocks.tallgrass) || world.getBlockState(pos).equals(Blocks.snow_layer.getDefaultState())){
				world.setBlockState(pos, leaves.getStateFromMeta(leafMeta));//getDefaultState().withProperty(BlockHighlandsLeaves.CHECK_DECAY, true).withProperty(BlockHighlandsLeaves.DECAYABLE, true));
			}
    	}
    	catch(RuntimeException e){
    		System.out.println("Error: Tree block couldn't generate!");
    		//e.printStackTrace();
    	}
    }
    
    /**
     * Directions are:
     * 0 = up/down (Y)
     * 4 = east/west (X)
     * 8 = north/south (Z)
     */
    protected void setBlockLog(BlockPos pos, int direction){
    	try{
			if(world.getBlockState(pos).getBlock().isReplaceable(world, pos) ||
					world.isAirBlock(pos) ||
					world.getBlockState(pos).getBlock() instanceof BlockHighlandsSapling){
				world.setBlockState(pos, wood.getStateFromMeta(woodMeta+direction));
			}
    	}
    	catch(RuntimeException e){
    		System.out.println("Error: Tree block couldn't generate!");
    		//e.printStackTrace();
    	}
    }
    
    
    
    protected boolean isCubeClear (BlockPos pos, int radius, int height){
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();
    	for(int i = x-radius; i <= x+radius; i++){
    		for(int k = z-radius; k <= z+radius; k++){
    			for(int j = y; j <= y+height; j++){
    				BlockPos pos2 = new BlockPos(i, j, k);
    				
    				if(!(world.isAirBlock(pos2) || world.getBlockState(pos2).getBlock().isLeaves(world, pos2)))
    					return false;
    			}
    		}
    	}
    	return true;
    }
    
    
    
}













