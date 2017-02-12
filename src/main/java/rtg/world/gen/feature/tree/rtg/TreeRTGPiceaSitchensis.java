package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Picea Sitchensis (Sitka Spruce)
 */
public class TreeRTGPiceaSitchensis extends TreeRTG
{

	/**
	 * <b>Picea Sitchensis (Sitka Spruce)</b><br><br>
	 * <u>Relevant variables:</u><br>
	 * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
	 * <u>DecoTree example:</u><br>
	 * DecoTree decoTree = new DecoTree(new TreeRTGPiceaSitchensis());<br>
	 * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
	 * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
	 * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
	 * decoTree.treeConditionNoise = 0f;<br>
	 * decoTree.treeConditionChance = 4;<br>
	 * decoTree.logBlock = Blocks.log;<br>
	 * decoTree.logMeta = (byte)1;<br>
	 * decoTree.leavesBlock = Blocks.leaves;<br>
	 * decoTree.leavesMeta = (byte)1;<br>
	 * decoTree.minTrunkSize = 4;<br>
	 * decoTree.maxTrunkSize = 9;<br>
	 * decoTree.minCrownSize = 5;<br>
	 * decoTree.maxCrownSize = 14;<br>
	 * decoTree.noLeaves = false;<br>
	 * this.addDeco(decoTree);
	 * 
	 */
	public TreeRTGPiceaSitchensis()
	{
		super();
		
		this.logBlock = Blocks.log;
		this.logMeta = (byte)1;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)1;
		this.trunkSize = 8;
		this.crownSize = 10;
		this.noLeaves = false;
		
		this.validGroundBlocks = new ArrayList<Block>(Arrays.asList(Blocks.grass, Blocks.dirt));
	}

	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {

    	Block g = world.getBlock(x, y - 1, z);
    	boolean validGroundBlock = false;
    	
    	for (int i = 0; i < this.validGroundBlocks.size(); i++) {
    		if (g == this.validGroundBlocks.get(i)) {
    			validGroundBlock = true;
    			break;
    		}
    	}
    	
    	if (!validGroundBlock) {
    		return false;
    	}

    	int i;
    	for(i = 0; i < this.trunkSize; i++)
    	{
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    		y++;
    	}

    	int pX = 0;
    	int pZ = 0;
    	for(i = 0; i < this.crownSize; i++)
    	{
    		if(rand.nextInt(2) == 0 && i < this.crownSize - 2)
    		{
    			int dX = -1 + rand.nextInt(3);
    			int dZ = -1 + rand.nextInt(3);

    			if(dX == 0 && dZ == 0)
    			{
    				dX = -1 + rand.nextInt(3);
    				dZ = -1 + rand.nextInt(3);
    			}

    			if(pX == dX && rand.nextBoolean())
    			{
    				dX = -dX;
    			}
    			if(pZ == dZ && rand.nextBoolean())
    			{
    				dZ = -dZ;
    			}

    			pX = dX;
    			pZ = dZ;

        		buildBranch(world, rand, x, y, z, dX, dZ, i < this.crownSize - 10 ? 2 : 1, i < this.crownSize - 6 ? 2 : 1);
    		}
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);

    		if(i < this.crownSize - 2)
	    	{
	    		if(rand.nextBoolean()) { buildLeaves(world, x, y, z + 1); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x, y, z - 1); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x + 1, y, z); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x - 1, y, z); }
    		}

    		y++;
    	}

    	buildLeaves(world, x, y - 1, z + 1);
    	buildLeaves(world, x, y - 1, z - 1);
    	buildLeaves(world, x + 1, y - 1, z);
    	buildLeaves(world, x - 1, y - 1, z);
    	buildLeaves(world, x, y, z);

    	return true;
    }
	
	@Override
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize)
    {
    	for(int i = -1; i <= 1; i++)
    	{
    		for(int j = -1; j <= 1; j++)
    		{
    			for(int k = 0; k < 2; k++)
    			{
    				if(Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1)
    				{
        				buildLeaves(world, x + i+ (dX * logLength), y + k, z + j + (dZ * logLength));
    				}
    			}
    		}
    	}

    	for(int m = 1; m <= logLength; m++)
    	{
        	this.placeLogBlock(world, x + (dX * m), y, z + (dZ * m), this.logBlock, this.logMeta, this.generateFlag);
    	}
    }
	
	@Override
    public void buildLeaves(World world, int x, int y, int z)
    {
		if (!this.noLeaves) {

	    	Block b = world.getBlock(x, y, z);
	    	if(b.getMaterial() == Material.air)
	    	{
	    		this.placeLeavesBlock(world, x, y, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	}
		}
    }
}
