package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TreeRTG extends WorldGenerator
{

	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int trunkSize;
	public int crownSize;
	public boolean noLeaves;
	
	public TreeRTG()
	{
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)0;
		this.trunkSize = 2;
		this.crownSize = 4;
		this.noLeaves = false;
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
