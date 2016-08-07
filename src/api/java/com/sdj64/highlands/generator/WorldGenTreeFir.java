package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTreeFir extends WorldGenMTreeBase
{
    public WorldGenTreeFir(Block leafBlock, Block woodBlock, int leafBlockMeta,
			int woodBlockMeta, int minH, int maxH, boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}



    public boolean generate(World wor, Random rand, BlockPos pos)
    {
    	this.world = wor;
    	this.random = rand;
    	
    	boolean isWide = (random.nextInt(3) == 0);
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);
    	
    	boolean trunk2 = random.nextInt(6)==0;
    	
        if(!isLegalTreePosition(pos, true, false))return false;
        if(!isCubeClear(pos.up(3), 2, 10))return false;
    	
		//generates the trunk
    	genTree(pos, treeHeight, isWide);
    	
    	
    	if(trunk2){
    		genTree(pos.west(), treeHeight, isWide);
    		genTree(pos.south(), treeHeight, isWide);
    		genTree(pos.west().south(), treeHeight, isWide);
    	}
    	
    	return true;
    }
    
    
    //TREE GENERATORS
    
	private boolean genTree(BlockPos pos, int treeHeight, boolean isWide){
    	//generates the trunk
    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.up(i), 0);
    	}
    	
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
    	
    	//generates the leaves
    	double leafRadius = 3.5;
    	double finalRadius = 1.5;
    	
    	int h0 = 6;
    	if(treeHeight < 16) h0 = 3;
    	int h;
    	for(h = locY + h0; h < treeHeight + locY; h++){
    		//level 1
    		if(isWide){
    			generateLeafLayerCircleNoise(leafRadius + 1, locX, locZ, h);
    			h++;
    		}
    		//level 2
    		generateLeafLayerCircleNoise(leafRadius, locX, locZ, h);
    		h++;
    		//level 3
    		generateLeafLayerCircleNoise(leafRadius - 1, locX, locZ, h);
    		//if not wide, gen a smaller layer
    		if(!isWide){
    			h++;
    			generateLeafLayerCircleNoise(leafRadius - 1.5, locX, locZ, h);
    		}
    		
    		leafRadius -= (leafRadius - finalRadius)/3.0;
    		
    	}
    	//generate top of tree
    	if(isWide){
    		generateLeafLayerCircleNoise(2, locX, locZ, h);
    		h++;
    	}
    	generateLeafLayerCircleNoise(1.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(1, locX, locZ, h);
    	h++;
    	setBlockLeaf(new BlockPos(locX, h, locZ));
    	h++;
    	setBlockLeaf(new BlockPos(locX, h, locZ));
    	return true;
    }
    
}













