package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTreeAspen extends WorldGenMTreeBase
{

    public WorldGenTreeAspen(Block leafBlock, Block woodBlock, int leafBlockMeta,
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
        if(!isCubeClear(pos.up(2), 2, 10)){
        	return false;
        }
        
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
        //generates trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.up(i), 0);
    	}
    	//generates leaves at top
    	int h;
    	for(h = locY+treeHeight - 3; h < locY + treeHeight; h++){
    		generateLeafLayerCircleNoise(3.5, locX, locZ, h);
    	}
    	generateLeafLayerCircleNoise(3.8, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(2.5, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(2, locX, locZ, h);
    	h++;
    	generateLeafLayerCircleNoise(1, locX, locZ, h);
    	h -= 12;
    	//generates branches / leaves
    	int firstDir = random.nextInt(4);
    	for(int i = 0; i < 6; i++){
    		BlockPos end = generateStraightBranch(4, locX, h+i, locZ, (firstDir + i)%4);
    		int[] xyz = {end.getX(), end.getY(), end.getZ()};
    		generateLeafLayerCircleNoise(1.8, xyz[0], xyz[2], xyz[1]-1);
    		generateLeafLayerCircleNoise(2.5, xyz[0], xyz[2], xyz[1]);
    		generateLeafLayerCircleNoise(1.8, xyz[0], xyz[2], xyz[1]+1);
    	}
    	return true;
    }
}













