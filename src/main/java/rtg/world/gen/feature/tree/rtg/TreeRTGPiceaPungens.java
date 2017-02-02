package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Picea Pungens (Colorado Spruce)
 */
public class TreeRTGPiceaPungens extends TreeRTG
{

	/**
	 * <b>Picea Pungens (Colorado Spruce)</b><br><br>
	 * <u>Relevant variables:</u><br>
	 * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
	 * <u>DecoTree example:</u><br>
	 * DecoTree decoTree = new DecoTree(new TreeRTGPiceaPungens());<br>
	 * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
	 * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
	 * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
	 * decoTree.treeConditionNoise = 0f;<br>
	 * decoTree.treeConditionChance = 4;<br>
	 * decoTree.logBlock = Blocks.log;<br>
	 * decoTree.logMeta = (byte)1;<br>
	 * decoTree.leavesBlock = Blocks.leaves;<br>
	 * decoTree.leavesMeta = (byte)1;<br>
	 * decoTree.minTrunkSize = 2;<br>
	 * decoTree.maxTrunkSize = 7;<br>
	 * decoTree.minCrownSize = 6;<br>
	 * decoTree.maxCrownSize = 17;<br>
	 * decoTree.noLeaves = false;<br>
	 * this.addDeco(decoTree);
	 * 
	 */
	public TreeRTGPiceaPungens()
	{
		super();
	}

	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	    	
    	int small = (int)Math.ceil((double)(this.crownSize / 2));
    	int large = small;
    	
    	int i, j, k;
    	for(i = 0; i < this.trunkSize; i++)
    	{
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    		y++;
    	}

    	for(i = 0; i < large; i++)
    	{
    		if (!this.noLeaves) {
    			
	    		for(j = -2; j <= 2; j++)
	    		{
	    			for(k = -2; k <= 2; k++)
	    			{
	    				if(Math.abs(j) + Math.abs(k) != 4 && ((j > -2 && k > -2 && j < 2 && k < 2) || rand.nextInt(4) != 0))
	    				{
	    					this.placeLeavesBlock(world, x + j, y, z + k, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    				}
	    			}
	    		}
    		}
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    		y++;
    	}
    	
    	for(i = 0; i < small; i++)
    	{
    		if (!this.noLeaves) {
    			
	    		for(j = -1; j <= 1; j++)
	    		{
	    			for(k = -1; k <= 1; k++)
	    			{
	    				if(Math.abs(j) + Math.abs(k) < 2 || (rand.nextInt(4) != 0))
	    				{
	    					this.placeLeavesBlock(world, x + j, y, z + k, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    				}
	    			}
	    		}
	    		
	    		if(i == 0)
	    		{
	    			this.placeLeavesBlock(world, x + 1, y, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	    	this.placeLeavesBlock(world, x - 1, y, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	    	this.placeLeavesBlock(world, x, y, z + 1, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	    	this.placeLeavesBlock(world, x, y, z - 1, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	    	this.placeLeavesBlock(world, x + 2, y, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	    	this.placeLeavesBlock(world, x - 2, y, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	    	this.placeLeavesBlock(world, x, y, z + 2, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	    	this.placeLeavesBlock(world, x, y, z - 2, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    		}
    		}
    		
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    		y++;
    	}
    	
		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
		
		if (!this.noLeaves) {
			
	    	this.placeLeavesBlock(world, x + 1, y, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	this.placeLeavesBlock(world, x - 1, y, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	this.placeLeavesBlock(world, x, y, z + 1, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	this.placeLeavesBlock(world, x, y, z - 1, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	
	    	this.placeLeavesBlock(world, x, y + 1, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    	this.placeLeavesBlock(world, x, y + 2, z, this.leavesBlock, this.leavesMeta, this.generateFlag);
		}
		
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
