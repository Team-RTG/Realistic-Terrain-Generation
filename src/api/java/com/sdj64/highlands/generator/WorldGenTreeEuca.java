package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTreeEuca extends WorldGenMTreeBase
{

    public WorldGenTreeEuca(Block leafBlock, Block woodBlock, int leafBlockMeta,
			int woodBlockMeta, int minH, int maxH, boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}


    public boolean generate(World wor, Random rand, BlockPos pos)
    {
    	world = wor;
    	random = rand;
        
        if(!isLegalTreePosition(pos, false, false))return false;
        if(!isCubeClear(pos.up(2), 1, 10))return false;
        
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
      //generates trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.down(-i), 0);
    	}
    	//generates leaves
    	int h = locY+treeHeight - 5;
    	generateLeafLayerCircleNoise(1.3, locX, locZ, h);
    	generateLeafLayerCircleNoise(2.0, locX, locZ, (h+1));
    	int i;
    	for(i = 1; i < random.nextInt(2)+2; i++){
        	generateLeafLayerCircleNoise(2.5, locX, locZ, h+i+1);
    	}
    	generateLeafLayerCircleNoise(3.5, locX, locZ, h+i+1);
    	generateLeafLayerCircleNoise(4.5, locX, locZ, h+i+2);
    	generateLeafLayerCircleNoise(2.5, locX, locZ, h+i+3);
    	generateLeafLayerCircleNoise(1.5, locX, locZ, h+i+4);
    	
    	if(random.nextBoolean()){
    		h = locY + treeHeight - 8;
    		generateLeafLayerCircleNoise(1.2, locX, locZ, (h-1));
    		generateLeafLayerCircleNoise(1.8, locX, locZ, h);
    		generateLeafLayerCircleNoise(1.2, locX, locZ, (h+1));
    	}
    	return true;
    }
}













