package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTreeBamboo extends WorldGenMTreeBase
{

    public WorldGenTreeBamboo(Block leafBlock, Block woodBlock, int leafBlockMeta,
			int woodBlockMeta, int minH, int maxH, boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}


    public boolean generate(World wor, Random rand, BlockPos pos)
    {
    	world = wor;
    	random = rand;
        
        if(!isLegalTreePosition(pos, false, false)){
        	return false;
        }
        if(!isCubeClear(pos.up(1), 1, 7)){
        	return false;
        }
        
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
        double leafWidth = 0;
        
        //generates trunk and leaves
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.up(i), 0);
    		
    		leafWidth = (double)i/(double)treeHeight * 1.9;
    		generateLeafLayerCircleNoise(leafWidth, locX, locZ, locY+i);
    	}
    	generateLeafLayerCircleNoise(1.9, locX, locZ, locY+treeHeight);
    	generateLeafLayerCircleNoise(1.9, locX, locZ, locY+treeHeight + 1);
    	
    	return true;
    }
}













