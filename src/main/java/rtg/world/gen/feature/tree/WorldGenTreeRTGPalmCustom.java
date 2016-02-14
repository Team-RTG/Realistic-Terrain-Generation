package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.util.BlockPos;
import rtg.config.rtg.ConfigRTG;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGPalmCustom extends WorldGenerator
{
	private static int leavesLength = 133;
	private static int[] leaves = new int[]{
		1, 0, 0,
		2, 0, 0,
		3, -1, 0,
		3, -2, 0,
		-1, 0, 0,
		-2, 0, 0,
		-3, -1, 0,
		-3, -2, 0,
		0, 0, 1,
		0, 0, 2,
		0, -1, 3,
		0, -2, 3,	
		0, 0, -1,
		0, 0, -2,
		0, -1, -3,
		0, -2, -3,	
		0, 1, 0,
		1, 1, 1,
		-1, 1, -1,
		-1, 1, 1,
		1, 1, -1,
		1, 2, 0,
		-1, 2, 0,
		0, 2, 1, 
		0, 2, -1,
		2, 3, 0,
		3, 3, 0,
		4, 2, 0, 
		-2, 3, 0,
		-3, 3, 0,
		-4, 2, 0, 
		0, 3, 2,
		0, 3, 3,
		0, 2, 4, 
		0, 3, -2,
		0, 3, -3,
		0, 2, -4, 
		2, 2, -2,
		-2, 2, 2,
		-2, 2, -2, 
		2, 2, 2,
		3, 2, -3,
		-3, 2, 3,
		-3, 2, -3, 
		3, 2, 3
	};

	private static int cocoasLength = 16;
	private static int[] cocoas = new int[]{
		2, 0, -2, 1,
		1, 1, -2, 0,
		0, 0, -2, -1,
		3, -1, -2, 0
	};
	
	private float treeLength = 15f;
	
    public WorldGenTreeRTGPalmCustom(float pTreeLength, int pLeavesLength, int[] pLeaves, int pCocoasLength, int[] pCocoas)
    {
        treeLength = pTreeLength;
        leavesLength = pLeavesLength;
        leaves = pLeaves;
        cocoasLength = pCocoasLength;
        cocoas = pCocoas;
    }
    
    public WorldGenTreeRTGPalmCustom()
    {

    }
    
    public WorldGenTreeRTGPalmCustom(float pTreeLength)
    {
        treeLength = pTreeLength;
    }
	
	@Override
	public boolean generate(World world, Random rand, BlockPos blockPos)
	{
    	Block b = world.getBlockState(blockPos.down()).getBlock();
    	
    	if(b != Blocks.grass && b != Blocks.dirt && b != Blocks.sand)
    	{
    		return false;
    	}
    	
    	double horDir = getRandomDir(rand);
    	float verDir = 0.3f + rand.nextFloat() * 0.4f, length = treeLength, c = 0f, loss = 0f;
    	
		if(verDir < 0f)
		{
			verDir = -verDir;
		}

		float velY = 1f - verDir;
		
		if(verDir > 1f)
		{
			verDir = 1f - (verDir - 1f);
		}
		
		float velX = (float)Math.cos(horDir * Math.PI / 180D) * verDir;
		float velZ = (float)Math.sin(horDir * Math.PI / 180D) * verDir;

		while (c < length) {
			world.setBlockState(blockPos, Blocks.log.getStateFromMeta(15));

			if (c < length - 3) {
				loss = Math.abs(velX) + Math.abs(velZ);
				velX *= 0.9f;
				velZ *= 0.9f;
				loss = loss - (Math.abs(velX) + Math.abs(velZ));
				velY += loss;
				blockPos = blockPos.add(velX, velY, velZ);
			} else {
				blockPos = blockPos.add(0, velY, 0);
			}

			c += 1f;
		}
		
		blockPos = blockPos.down();

		for (int j = 0; j < leavesLength; j += 3) {
			world.setBlockState(blockPos.add(leaves[j], leaves[j + 1], leaves[j + 2]), Blocks.leaves.getStateFromMeta(3), 2);
		}

		for (int k = 0; k < cocoasLength; k += 4) {
			if (rand.nextInt(20) == 0) {
				world.setBlockState(blockPos.add(cocoas[k + 1], cocoas[k + 2], cocoas[k + 3]), Blocks.cocoa.getStateFromMeta(cocoas[k] + 8), 2);
			}
		}
    	
		return true;
	}
	
	public double getRandomDir(Random rand)
	{
		switch(rand.nextInt(4))
		{
			case 0: return -180D;
			case 1: return -90D;
			case 2: return 0D;
			case 3: return 90D;
		}
		return 0D;
	}
}
