package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTreeSpiral extends WorldGenMTreeBase
{


    public WorldGenTreeSpiral(Block leafBlock, Block woodBlock,
			int leafBlockMeta, int woodBlockMeta, int minH, int maxH,
			boolean notify) {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, minH, maxH, notify);
	}

	public boolean generate(World wor, Random rand, BlockPos pos)
    {
    	world = wor;
    	random = rand;
        
        if(!isLegalTreePosition(pos, false, false))return false;
        if(!isCubeClear(pos.down(-2), 5, 12))return false;
        
        int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
    	//generates the trunk
    	int treeHeight = minHeight + random.nextInt(maxHeight-minHeight);
    	double primaryRadius = 4;
    	double finalRadius = 1;
    	double secondaryRadius = 1.8;
    	double finalRadius2 = 1;
    	double angleRad = 0;
    	double angleRad2 = random.nextDouble()*6.28;
    	int nextBranch = 3 + random.nextInt(2);
    	
    	locX-= primaryRadius;
    	
    	for(int i = 0; i < treeHeight; i++){
    		//setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
			int x = (int)(primaryRadius * Math.cos(angleRad));
			int z = (int)(primaryRadius * Math.sin(angleRad));
			
			if(i == 0) generateWoodLayerCircle(secondaryRadius+0.5, x+locX, z+locZ, i+locY);
			else generateWoodLayerCircle(secondaryRadius, x+locX, z+locZ, i+locY);
			
			if(nextBranch == 0 && i < treeHeight - 5){
				int x2 = 0;
				int z2 = 0;
				int branchLength = i < treeHeight-7 ? 7 : 4;
				for(double j = 0; j < branchLength; j++){
					x2 = (int)(j * Math.cos(angleRad2));
					z2 = (int)(j * Math.sin(angleRad2));
					
					setBlockLog(new BlockPos(x+x2+locX, i+locY, z+z2+locZ), 0);
				}
				generateLeafLayerCircleNoise(secondaryRadius+2, x+x2+locX, z+z2+locZ, i+locY+1);
				generateLeafLayerCircleNoise(secondaryRadius+1, x+x2+locX, z+z2+locZ, i+locY+2);
				nextBranch = 1+random.nextInt(1);
				angleRad2 += 1+random.nextDouble();
			}
			else nextBranch--;
			
    		angleRad += Math.PI / 8;
    		primaryRadius -= (primaryRadius - finalRadius)/10.0;
    		secondaryRadius -= (secondaryRadius - finalRadius2)/10.0;
    		
    		if(i >= treeHeight -1){
    			for(int j = -1; j < 6; j++){
    				generateLeafLayerCircleNoise(secondaryRadius + 3 - j, x+locX, z+locZ, i+locY + j);
    			}
    		}
    	}
		return true;
    }
}













