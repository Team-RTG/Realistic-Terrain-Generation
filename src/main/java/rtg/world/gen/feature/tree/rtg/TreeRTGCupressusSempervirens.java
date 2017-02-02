package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Cupressus Sempervirens (Italian Cypress)
 */
public class TreeRTGCupressusSempervirens extends TreeRTG
{

	/**
	 * <b>Cupressus Sempervirens (Italian Cypress)</b><br><br>
	 * <u>Relevant variables:</u><br>
	 * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
	 * <u>DecoTree example:</u><br>
	 * DecoTree decoTree = new DecoTree(new TreeRTGCupressusSempervirens());<br>
	 * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
	 * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
	 * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
	 * decoTree.treeConditionNoise = 0f;<br>
	 * decoTree.treeConditionChance = 4;<br>
	 * decoTree.logBlock = Blocks.log;<br>
	 * decoTree.logMeta = (byte)1;<br>
	 * decoTree.leavesBlock = Blocks.leaves;<br>
	 * decoTree.leavesMeta = (byte)1;<br>
	 * decoTree.minTrunkSize = 3;<br>
	 * decoTree.maxTrunkSize = 6;<br>
	 * decoTree.minCrownSize = 5;<br>
	 * decoTree.maxCrownSize = 10;<br>
	 * decoTree.noLeaves = false;<br>
	 * this.addDeco(decoTree);
	 * 
	 */
	public TreeRTGCupressusSempervirens()
	{
		super();
		
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
    	
    	int i, j, k;
    	for(i = 0; i < this.trunkSize; i++)
    	{
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    		y++;
    	}

    	int small = (int)Math.ceil((double)(this.crownSize / 2));
    	int large = small;
    	
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
}
