package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import rtg.config.rtg.ConfigRTG;
import rtg.util.WorldUtil;

/**
 * The base class for all RTG trees.
 * 
 * @see <a href="http://imgur.com/a/uoJsU">RTG Tree Gallery</a>
 * @author WhichOnesPink
 *
 */
public class TreeRTG extends WorldGenAbstractTree
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

    protected int trunkLogMeta;
    private boolean allowBarkCoveredLogs;

    public TreeRTG(boolean notify) {
        super(notify);
    }

	public TreeRTG() {

	    this(false);

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

        this.allowBarkCoveredLogs = ConfigRTG.allowBarkCoveredLogs;
        this.trunkLogMeta = this.logMeta;
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

    protected boolean isGroundValid(World world, int x, int y, int z) {

        return this.isGroundValid(world, x, y, z, ConfigRTG.allowTreesToGenerateOnSand);
    }

    protected boolean isGroundValid(World world, int x, int y, int z, boolean sandAllowed) {

        Block g = world.getBlock(x, y - 1, z);

        if (g == Blocks.sand && !sandAllowed) {
            return false;
        }

        for (int i = 0; i < this.validGroundBlocks.size(); i++) {
            if (g == this.validGroundBlocks.get(i)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isGroundValid(World world, ArrayList<Integer> x, ArrayList<Integer> y, ArrayList<Integer> z) {

        if (x.isEmpty() || y.isEmpty() || z.isEmpty()) {
            throw new RuntimeException("Unable to determine if ground is valid. No trunks.");
        }

        for (int i = 0; i < x.size(); i++) {
            if (!this.isGroundValid(world, x.get(i), y.get(i), z.get(i))) {
                return false;
            }
        }

        return true;
    }

    protected void placeLogBlock(World world, int x, int y, int z, Block logBlock, byte logMeta, int generateFlag) {

        if (this.isReplaceable(world, x, y, z)) {
            world.setBlock(x, y, z, logBlock, logMeta, generateFlag);
        }
    }

    protected void placeLeavesBlock(World world, int x, int y, int z, Block leavesBlock, byte leavesMeta, int generateFlag) {

        if (world.isAirBlock(x, y, z)) {
            world.setBlock(x, y, z, leavesBlock, leavesMeta, generateFlag);
        }
    }

    @Override
    public boolean isReplaceable(World world, int x, int y, int z) {

        Block b = world.getBlock(x, y, z);

        return b.isAir(world, x, y, z)
            || b.isLeaves(world, x, y, z)
            || b.isWood(world, x, y, z)
            || canGrowInto(b);
    }

    @Override
    protected boolean func_150523_a(Block blockType) {

        return this.canGrowInto(blockType);
    }

    protected boolean canGrowInto(Block blockType) {

        Material material = blockType.getMaterial();

        return material == Material.air
            || material == Material.leaves
            || material == Material.plants
            || material == Material.grass
            || material == Material.ground
            || material == Material.wood
            || material == Material.vine
            || material == Material.water
            || material == Material.snow;
    }

    public boolean hasSpaceToGrow(World world, Random rand, int x, int y, int z, int treeHeight) {

        WorldUtil worldUtil = new WorldUtil(world);

        if (!worldUtil.isSurroundedByBlock(Blocks.air, treeHeight, WorldUtil.SurroundCheckType.UP, rand, x, y, z)) {

            //Logger.debug("Unable to grow RTG tree with %d height. Something in the way.", treeHeight);

            return false;
        }

        return true;
    }

    public int getTrunkLogMeta(int defaultMeta) {

        if (!this.allowBarkCoveredLogs) {
            return defaultMeta;
        }

        return defaultMeta + 12;
    }

    public int getTrunkLogMeta(byte defaultMeta) {

        return getTrunkLogMeta((int)defaultMeta);
    }
}
