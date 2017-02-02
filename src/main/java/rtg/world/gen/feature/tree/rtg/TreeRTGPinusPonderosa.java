package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Pinus Ponderosa (Ponderosa Pine)
 */
public class TreeRTGPinusPonderosa extends TreeRTG
{

	/**
	 * <b>Pinus Ponderosa (Ponderosa Pine)</b><br><br>
	 * <u>Relevant variables:</u><br>
	 * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
	 * <u>DecoTree example:</u><br>
	 * DecoTree decoTree = new DecoTree(new TreeRTGPinusPonderosa());<br>
	 * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
	 * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
	 * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
	 * decoTree.treeConditionNoise = 0f;<br>
	 * decoTree.treeConditionChance = 4;<br>
	 * decoTree.logBlock = Blocks.log;<br>
	 * decoTree.logMeta = (byte)0;<br>
	 * decoTree.leavesBlock = Blocks.leaves;<br>
	 * decoTree.leavesMeta = (byte)0;<br>
	 * decoTree.minTrunkSize = 11;<br>
	 * decoTree.maxTrunkSize = 21;<br>
	 * decoTree.minCrownSize = 15;<br>
	 * decoTree.maxCrownSize = 29;<br>
	 * decoTree.noLeaves = false;<br>
	 * this.addDeco(decoTree);
	 * 
	 */
	public TreeRTGPinusPonderosa()
	{
		super();
		
		this.validGroundBlocks = new ArrayList<Block>(Arrays.asList(Blocks.grass, Blocks.dirt));
	}
	
	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	int startY = y;
    	
    	Block g = world.getBlock(x, y - 1, z);
    	boolean validGroundBlock = false;

		this.trunkLogMeta = this.getTrunkLogMeta(this.logMeta);
    	
    	for (int i = 0; i < this.validGroundBlocks.size(); i++) {
    		if (g == this.validGroundBlocks.get(i)) {
    			validGroundBlock = true;
    			break;
    		}
    	}
    	
    	if (!validGroundBlock) {
    		return false;
    	}
    	
    	buildTrunk(world, rand, x + 1, y, z);
    	buildTrunk(world, rand, x - 1, y, z);
    	buildTrunk(world, rand, x, y, z + 1);
    	buildTrunk(world, rand, x, y, z - 1);
    	
    	int i;
    	for(i = 0; i < trunkSize; i++)
    	{
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    		if(i > 5 && rand.nextInt(7) == 0)
    		{
    			int dX = -1 + rand.nextInt(3);
    			int dZ = -1 + rand.nextInt(3);
    			
    			if(dX == 0 && dZ == 0)
    			{
    				dX = -1 + rand.nextInt(3);
    				dZ = -1 + rand.nextInt(3);
    			}
    			
    			buildBranch(world, rand, x, y, z, dX, dZ, 1, 1);
    		}
    		
    		y++;
    	}
    	
    	int pX = 0;
    	int pZ = 0;
    	int j;
    	for(i = 0; i < crownSize; i++)
    	{
    		if(rand.nextInt(i < crownSize - 12 && i > 2 ? 2 : 1) == 0 && i < crownSize - 2)
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

        		buildBranch(world, rand, x, y, z, dX, dZ, 
        			i < crownSize - 12 && i > 3 ? 3 : i < crownSize - 8 ? 2 : 1, 
        			i < crownSize - 5 ? 2 : 1
        		);
    		}
    		this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    		
    		if(i < crownSize - 2)
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
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {
    	int h = (int)Math.ceil(this.trunkSize / 4f);
    	h = h + rand.nextInt(h * 2);
    	for(int i = -1; i < h; i++)
    	{
    		this.placeLogBlock(world, x, y + i, z, this.logBlock, (byte)this.trunkLogMeta, this.generateFlag);
    	}
    }
	
	@Override
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize)
    {
    	if(logLength == 3 && Math.abs(dX) + Math.abs(dZ) == 2)
    	{
    		logLength--;
    	}
    	
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
