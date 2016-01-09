package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeMangrove extends WorldGenerator
{
	private Block blockLog;
	private int metadataLog;
	private Block blockLeaves;
	private int metadataLeaves;
	
	private int base;
	private int root;
	private float length;
	
	private int branch;
	private float verStart;
	private float verRand;
	
	/*
	 * Blocks.log, 0, Blocks.leaves, 0, 9 + rand.nextInt(5), 3 + rand.nextInt(2), 13f, 3, 0.32f, 0.1f
	 */
	
	public WorldGenTreeMangrove(Block log, int metaLog, Block leaves, int metaLeaves, int baseHeight, int rootHeight, float branchLength, int numBranches, float verticalStart, float verticalRand)
	{
		blockLog = log;
		metadataLog = metaLog;
		blockLeaves = leaves;
		metadataLeaves = metaLeaves;
		
		base = baseHeight;
		root = rootHeight;
		length = branchLength;
		
		branch = numBranches;
		verStart = verticalStart;
		verRand = verticalRand;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
    	Block b = world.getBlock(x, y - 1, z);
    	if(b != Blocks.grass && b != Blocks.dirt && b != Blocks.sand)
    	{
    		if(!(b == Blocks.water && world.getBlock(x, y - 2, z) == Blocks.sand && world.getBlock(x, y, z) == Blocks.air))
    		{
    			return false;
    		}
    	}
    	
    	if(root > 0f)
    	{
	    	for(int k = 0; k < 3; k++)
	    	{
	    		generateBranch(world, rand, x, y + root, z, (120 * k) - 40 + rand.nextInt(80), 1.6f + rand.nextFloat() * 0.1f, root * 2f, 1f);
	    	}
    	}
    	
    	for(int i = y + root; i < y + base; i++)
    	{
    		world.setBlock(x, i, z, blockLog, metadataLog, 2);
    	}
    	
    	float horDir, verDir;
    	int eX, eY, eZ;
    	for(int j = 0; j < branch; j++)
    	{
    		horDir = (120 * j) - 60 + rand.nextInt(120);
    		verDir = verStart + rand.nextFloat() * verRand;
        	generateBranch(world, rand, x, y + base, z, horDir, verDir, length, 1f);
        	
        	eX = x + (int)(Math.cos(horDir * Math.PI / 180D) * verDir * length);
        	eZ = z + (int)(Math.sin(horDir * Math.PI / 180D) * verDir * length);
        	eY = y + base + (int)((1f - verDir) * length);
        	
        	for(int m = 0; m < 1; m++)
        	{
            	generateLeaves(world, rand, eX, eY, eZ, 4f, 1.2f);
        	}
    	}
		
		return true;
	}
	
	/*
	 * horDir = number between -180D and 180D
	 * verDir = number between 1F (horizontal) and 0F (vertical)
	 */
	public void generateBranch(World world, Random rand, float x, float y, float z, double horDir, float verDir, float length, float speed)
	{
		if(verDir < 0f)
		{
			verDir = -verDir;
		}

		float c = 0f;
		float velY = 1f - verDir;
		
		if(verDir > 1f)
		{
			verDir = 1f - (verDir - 1f);
		}
		
		float velX = (float)Math.cos(horDir * Math.PI / 180D) * verDir;
		float velZ = (float)Math.sin(horDir * Math.PI / 180D) * verDir;
		
		while(c < length)
		{
			world.setBlock((int)x, (int)y, (int)z, blockLog, metadataLog, 2);
			
			x += velX;
			y += velY;
			z += velZ;
			
			c += speed;
		}
	}
	
	public void generateLeaves(World world, Random rand, int x, int y, int z, float size, float width)
	{
		float dist;
		int i, j, k, s = (int)(size - 1f), w = (int)((size - 1f) * width);
		for(i = -w; i <= w; i++)
		{
			for(j = -s; j <= s; j++)
			{
				for(k = -w; k <= w; k++)
				{
					dist = Math.abs((float)i / width) + (float)Math.abs(j) + Math.abs((float)k / width);
					if(dist <= size - 0.5f || (dist <= size && rand.nextBoolean()))
					{
						if(dist < 0.6f)
						{
							world.setBlock(x + i, y + j, z + k, blockLog, metadataLog, 2);
						}
						if(world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlock(x + i, y + j, z + k, blockLeaves, metadataLeaves, 2);
						}
					}
				}
			}
		}
	}
}
