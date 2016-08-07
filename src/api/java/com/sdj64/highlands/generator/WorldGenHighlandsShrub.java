package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenHighlandsShrub extends WorldGenMTreeBase
{

    public WorldGenHighlandsShrub(Block leafBlock, Block woodBlock, int leafBlockMeta, int woodBlockMeta, boolean notify)
    {
		super(leafBlock, woodBlock, leafBlockMeta, woodBlockMeta, 1, 1, notify);
	}

	public boolean generate(World wor, Random rand, BlockPos pos)
    {
    	world = wor;
    	random = rand;
    	
        if(!isLegalTreePosition(pos, false, false)){
        	return false;
        }
    	
    	setBlockLog(pos, 0);
    	
    	generateLeafLayerCircleNoise(2.4, pos.getX(), pos.getZ(), pos.getY());
    	generateLeafLayerCircleNoise(1.2, pos.getX(), pos.getZ(), pos.getY()+1);
    	
        return true;
    }
}
