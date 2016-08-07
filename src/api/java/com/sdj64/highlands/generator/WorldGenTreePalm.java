package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTreePalm extends WorldGenMTreeBase
{

	//this array is the 8 directions of x and y, used for palm trees.
    private int[][]directions = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};

    public WorldGenTreePalm(Block leafBlock, Block woodBlock,
			int leafBlockMeta, int woodBlockMeta, int minH, int maxH,
			boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}

	public boolean generate(World wor, Random rand, BlockPos pos)
    {
		world = wor;
    	random = rand;
        
        if(!isLegalTreePosition(pos, false, true))return false;
        if(!isCubeClear(pos.up(2), 1, 5))return false;
    	
    	int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
        //generates trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);
    	for(int i = 0; i < treeHeight; i++){
    		setBlockLog(pos.up(i), 0);
    	}
    	//generates leaves
    	int h = locY + treeHeight;
    	BlockPos pos2 = new BlockPos(locX, h, locZ);
    	setBlockLeaf(pos2);
    	int r = 1;
    	genLeafAllDirections(pos2, r);
    	genLeafAllDirections(pos2.up(1), r);
    	r++;
    	genLeafAllDirections(pos2, r);
    	genLeafAllDirections(pos2.up(2), r);
    	genLeafAllDirections(pos2.up(3), r);
    	r++;
    	genLeafAllDirections(pos2, r);
    	genLeafAllDirections(pos2.down(), r);
    	r++;
    	genLeafAllDirections(pos2.down(), r);
    	
    	//generate cocoa (as coconuts) on 1/3 of trees
    	if(random.nextInt(3) == 0){
    		
    	}
		return true;
    }
	
	private void genLeafAllDirections(BlockPos pos, int r){
		for(int i = 0; i < directions.length; i++){
			setBlockLeaf(pos.east(directions[i][0]*r).north(directions[i][1]*r));
		}
	}
}













