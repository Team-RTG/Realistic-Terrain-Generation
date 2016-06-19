package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * The base class for all RTG trees.
 * 
 * @see <a href="http://imgur.com/a/uoJsU">RTG Tree Gallery</a>
 * @author WhichOnesPink
 *
 */
public class TreeRTG extends WorldGenerator
{

	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int trunkSize;
	public int crownSize;
	public boolean noLeaves;
	
	public Block saplingBlock;
	public byte saplingMeta;
	
	public int generateFlag;
	
	public int minTrunkSize;
	public int maxTrunkSize;
	public int minCrownSize;
	public int maxCrownSize;
	
	public ArrayList<Block> validGroundBlocks;
	
	public TreeRTG()
	{
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)0;
		this.trunkSize = 2;
		this.crownSize = 4;
		this.noLeaves = false;
		
		this.saplingBlock = Blocks.sapling;
		this.saplingMeta = (byte)0;
		
		this.generateFlag = 2;
		
		// These need to default to zero as they're only used when generating trees from saplings.
		this.minTrunkSize = 0;
		this.maxTrunkSize = 0;
		this.minCrownSize = 0;
		this.maxCrownSize = 0;
		
		// Each tree sub-class is responsible for using (or not using) this list as part of its generation logic.
		this.validGroundBlocks = new ArrayList<Block>(Arrays.asList(Blocks.grass, Blocks.dirt, Blocks.sand));
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		return false;
	}
	
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {

    }
    
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize)
    {

    }
	
    public void buildLeaves(World world, int x, int y, int z)
    {
    	if (this.noLeaves) {
    		return;
    	}
    }
    
    public void buildLeaves(World world, Random rand, int x, int y, int z, int size)
    {

    }
    
	public TreeRTG setLogBlock(Block logBlock)
	{
		this.logBlock = logBlock;
		return this;
	}
	
	public TreeRTG setLogMeta(byte logMeta)
	{
		this.logMeta = logMeta;
		return this;
	}
	
	public TreeRTG setLeavesBlock(Block leavesBlock)
	{
		this.leavesBlock = leavesBlock;
		return this;
	}
	
	public TreeRTG setLeavesMeta(byte leavesMeta)
	{
		this.leavesMeta = leavesMeta;
		return this;
	}
	
	public TreeRTG setTrunkSize(int trunkSize)
	{
		this.trunkSize = trunkSize;
		return this;
	}
	
	public TreeRTG setCrownSize(int crownSize)
	{
		this.crownSize = crownSize;
		return this;
	}
	
	public TreeRTG setNoLeaves(boolean noLeaves)
	{
		this.noLeaves = noLeaves;
		return this;
	}
}
