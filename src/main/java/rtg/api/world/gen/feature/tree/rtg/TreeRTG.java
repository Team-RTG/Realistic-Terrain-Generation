package rtg.api.world.gen.feature.tree.rtg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import rtg.RTGConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.Logger;
import rtg.api.util.RTGTreeData;
import rtg.api.world.deco.DecoBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The base class for all RTG trees.
 *
 * @author WhichOnesPink
 * @see <a href="http://imgur.com/a/uoJsU">RTG Tree Gallery</a>
 */
public abstract class TreeRTG extends WorldGenAbstractTree {
	
	public static final int ROOFED_FOREST_LIGHT_OBSTRUCTION_LIMIT = 6;

    protected IBlockState logBlock;
    protected IBlockState leavesBlock;
    protected IBlockState branchBlock;
    protected int trunkSize;
    protected int crownSize;
    protected boolean noLeaves;

    protected IBlockState saplingBlock;

    protected int generateFlag;

    protected int minTrunkSize;
    protected int maxTrunkSize;
    protected int minCrownSize;
    protected int maxCrownSize;
    
    protected float lowestVariableTrunkProportion = 0.25f;
    protected float trunkProportionVariability = 0.25f;
    protected int trunkReserve = 0;

    protected ArrayList<IBlockState> validGroundBlocks;
    protected ArrayList<Material> canGrowIntoMaterials;

    private boolean allowBarkCoveredLogs;
    protected int maxAllowedObstruction = 4;

    public TreeRTG(boolean notify) {

        super(notify);
    }

    public TreeRTG() {

        this(false);

        this.setLogBlock(Blocks.LOG.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        this.setBranchBlock(Blocks.LOG.getStateFromMeta(12));
        this.trunkSize = 2;
        this.crownSize = 4;
        this.setNoLeaves(false);

        this.saplingBlock = Blocks.SAPLING.getDefaultState();

        this.generateFlag = 19;

        // These need to default to zero as they're only used when generating trees from saplings.
        this.setMinTrunkSize(0);
        this.setMaxTrunkSize(0);
        this.setMinCrownSize(0);
        this.setMaxCrownSize(0);

        // Each tree sub-class is responsible for using (or not using) this list as part of its generation logic.
        this.validGroundBlocks = new ArrayList<>(Arrays.asList(
                Blocks.GRASS.getDefaultState(),
                Blocks.DIRT.getDefaultState(),
                BlockUtil.getStateDirt(BlockDirt.DirtType.PODZOL),
                BlockUtil.getStateSand(BlockSand.EnumType.RED_SAND)
        ));

        this.canGrowIntoMaterials = new ArrayList<>(Arrays.asList(
            Material.AIR,
            Material.WOOD,
            Material.LEAVES,
            Material.GRASS,
            Material.GROUND,
            Material.PLANTS,
            Material.VINE,
            Material.WATER,
            Material.SNOW
        ));

        this.allowBarkCoveredLogs = RTGConfig.barkCoveredLogs();
    }
    
    public TreeRTG(TreeRTG model) {

        this(false);

        this.setLogBlock(model.logBlock);
        this.setLeavesBlock(model.leavesBlock);
        this.trunkSize = model.trunkSize;
        this.crownSize = model.crownSize;
        this.setNoLeaves(model.noLeaves);

        this.saplingBlock = model.getSaplingBlock();

        this.generateFlag = model.generateFlag;

        // These need to default to zero as they're only used when generating trees from saplings.
        this.setMinTrunkSize(model.minTrunkSize);
        this.setMaxTrunkSize(model.maxTrunkSize);
        this.setMinCrownSize(model.minCrownSize);
        this.setMaxCrownSize(model.maxCrownSize);

        // Each tree sub-class is responsible for using (or not using) this list as part of its generation logic.
        this.validGroundBlocks = new ArrayList<>(model.validGroundBlocks.size());
        this.validGroundBlocks.addAll(model.validGroundBlocks);

        this.canGrowIntoMaterials = new ArrayList<>(model.canGrowIntoMaterials.size());
        this.canGrowIntoMaterials.addAll(model.canGrowIntoMaterials);

        this.allowBarkCoveredLogs = model.allowBarkCoveredLogs;
    }
    
    public float estimatedSize() {
    	//return estimated size of current tree in area. 1f is roughly an 8 diameter circle.
    	return 1f;
    }
    
    public int furthestLikelyExtension() { 
    	return 5;
    }

    public void buildTrunk(World world, Random rand, int x, int y, int z, SkylightTracker lightTracker) {

        int h = (int) Math.floor(this.trunkSize / 4f);
        h = h - 2 + rand.nextInt(4);
        if (h <= 0) return;
        for (int i = -1; i < h; i++) {
            this.placeTrunkBlock(world, new BlockPos(x, y + i, z), this.generateFlag, lightTracker);
        }
    }

    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize, SkylightTracker lightTracker) {

    }

    public void buildLeaves(World world, int x, int y, int z, SkylightTracker lightTracker) {

    }

    public void buildLeaves(World world, Random rand, int x, int y, int z, int size, SkylightTracker lightTracker) {

    }

    protected boolean isGroundValid(World world, BlockPos trunkPos) {

        return this.isGroundValid(world, trunkPos, RTGConfig.treesCanGenerateOnSand());
    }

    protected boolean isGroundValid(World world, BlockPos trunkPos, boolean sandAllowed) {

        IBlockState g = world.getBlockState(new BlockPos(trunkPos.getX(), trunkPos.getY() - 1, trunkPos.getZ()));

        if (g.getBlock() == Blocks.SAND && !sandAllowed) {
            return false;
        }

        for (int i = 0; i < this.validGroundBlocks.size(); i++) {
            if (g == this.validGroundBlocks.get(i)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isGroundValid(World world, ArrayList<BlockPos> trunkPos) {

        if (trunkPos.isEmpty()) {
            throw new RuntimeException("Unable to determine if ground is valid. No trunks.");
        }

        for (int i = 0; i < trunkPos.size(); i++) {
            if (!this.isGroundValid(world, trunkPos.get(i))) {
                return false;
            }
        }

        return true;
    }

    /*protected void placeLogBlock(World world, BlockPos pos, IBlockState logBlock, int generateFlag) {

        if (this.isReplaceable(world, pos)) {
            world.setBlockState(pos, logBlock, generateFlag);
        }
    }*/

    protected boolean placeLogBlock(World world, BlockPos pos, IBlockState logBlock, int generateFlag, SkylightTracker tracker) {

        if (this.isReplaceable(world, pos)) {
        	return tracker.testPlace(world, pos, logBlock, generateFlag);
        }
        return false;
    }
    
    protected boolean debugPlaceLogBlock(World world, BlockPos pos, IBlockState logBlock, int generateFlag, SkylightTracker tracker) {

        if (this.isReplaceable(world, pos)) {
        	return tracker.testPlace(world, pos, logBlock, generateFlag);
        }
        return false;
    }
    
    protected boolean placeTrunkBlock(World world, BlockPos pos, int generateFlag, SkylightTracker tracker) {

        if (this.isReplaceable(world, pos)) {
        	return tracker.testTrunk(world, pos, logBlock, generateFlag);
        }
        return false;
    }
    
    protected boolean placeLeavesBlock(World world, BlockPos pos, IBlockState leavesBlock, int generateFlag, SkylightTracker tracker) {

        if (world.isAirBlock(pos)) {
            return tracker.testPlace(world, pos, leavesBlock, generateFlag);
        }
        return (world.getBlockState(pos)==leavesBlock||world.getBlockState(pos)==logBlock||world.getBlockState(pos)==this.branchBlock); 
        // count as successful if already that tree. Logs and branches don't block because of problems getting away from the base trunk
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos) {

        IBlockState state = world.getBlockState(pos);

        return state.getBlock().isAir(state, world, pos)
                || state.getBlock().isLeaves(state, world, pos)
                || state.getBlock().isWood(world, pos)
                || state.getBlock().equals(Blocks.SAPLING)
                || canGrowInto(state.getBlock());
    }

    @Override
    protected boolean canGrowInto(Block block) {

        if (block instanceof BlockPlanks) {
            return false;
        }

        Material material = block.getDefaultState().getMaterial();

        for (int i = 0; i < this.canGrowIntoMaterials.size(); i++) {
            if (material == this.canGrowIntoMaterials.get(i)) {
                //Logger.debug("Log has grown into %s (%s)", this.canGrowIntoMaterials.get(i).toString(), block.getLocalizedName());
                return true;
            }
        }

        return false;
    }

    public IBlockState getTrunkLog(IBlockState defaultLog) {

        if (!this.allowBarkCoveredLogs) {
            return defaultLog;
        }

        IBlockState trunkLog;

        try {
            trunkLog = defaultLog.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE);
        }
        catch (Exception e) {
            trunkLog = defaultLog;
        }

        return trunkLog;
    }

    public IBlockState getLogBlock() {

        return logBlock;
    }

    public TreeRTG setLogBlock(IBlockState logBlock) {

        this.logBlock = logBlock;
        return this;
    }
    
    public IBlockState getBranchBlock() {

        return branchBlock;
    }

    public TreeRTG setBranchBlock(IBlockState branchBlock) {

        this.branchBlock = branchBlock;
        return this;
    }

    public IBlockState getLeavesBlock() {

        return leavesBlock;
    }

    public TreeRTG setLeavesBlock(IBlockState leavesBlock) {

        this.leavesBlock = leavesBlock;
        return this;
    }

    public int getTrunkSize() {

        return trunkSize;
    }

    public TreeRTG setTrunkSize(int trunkSize) {

        this.trunkSize = trunkSize;
        return this;
    }

    public int getCrownSize() {

        return crownSize;
    }

    public TreeRTG setCrownSize(int crownSize) {

        this.crownSize = crownSize;
        return this;
    }

    public boolean getNoLeaves() {

        return noLeaves;
    }

    public TreeRTG setNoLeaves(boolean noLeaves) {

        this.noLeaves = noLeaves;
        return this;
    }

    public IBlockState getSaplingBlock() {

        return saplingBlock;
    }

    public TreeRTG setSaplingBlock(IBlockState saplingBlock) {

        this.saplingBlock = saplingBlock;
        return this;
    }

    public int getGenerateFlag() {

        return generateFlag;
    }

    public TreeRTG setGenerateFlag(int generateFlag) {

        this.generateFlag = generateFlag;
        return this;
    }

    public int getMinTrunkSize() {

        return minTrunkSize;
    }

    public TreeRTG setMinTrunkSize(int minTrunkSize) {

        this.minTrunkSize = minTrunkSize;
        return this;
    }

    public int getMaxTrunkSize() {

        return maxTrunkSize;
    }

    public TreeRTG setMaxTrunkSize(int maxTrunkSize) {

        this.maxTrunkSize = maxTrunkSize;
        return this;
    }

    public int getMinCrownSize() {

        return minCrownSize;
    }

    public TreeRTG setMinCrownSize(int minCrownSize) {

        this.minCrownSize = minCrownSize;
        return this;
    }

    public int getMaxCrownSize() {

        return maxCrownSize;
    }

    public TreeRTG setMaxCrownSize(int maxCrownSize) {

        this.maxCrownSize = maxCrownSize;
        return this;
    }
    
    public float getLowestVariableTrunkProportion() {

        return lowestVariableTrunkProportion;
    }
    
    public TreeRTG setLowestVariableTrunkProportion (float lowest) {
    	lowestVariableTrunkProportion = lowest;
    	return this;
    }
    
    public float getTrunkProportionVariability() {

        return trunkProportionVariability;
    }
    
    public TreeRTG setTrunkProportionVariability (float variability) {
    	trunkProportionVariability = variability;
    	return this;
    }
    
    public int getTrunkReserve() {return this.trunkReserve;}

    public ArrayList<IBlockState> getValidGroundBlocks() {

        return validGroundBlocks;
    }

    public TreeRTG setValidGroundBlocks(ArrayList<IBlockState> validGroundBlocks) {

        this.validGroundBlocks = validGroundBlocks;
        return this;
    }
    
    public int getMaxAllowedObstruction() { return this.maxAllowedObstruction;}
    
    public void setMaxAllowedObstruction(int newObstruction) {this.maxAllowedObstruction = newObstruction;}
    
    public void randomizeTreeSize(Random rand) {
    	this.trunkSize = DecoBase.getRangedRandom(rand, minTrunkSize, maxTrunkSize);
    	this.crownSize = DecoBase.getRangedRandom(rand, minCrownSize, maxCrownSize);
    }
    
    protected static class FractionalBlockPos {
    	double x;
    	double y;
    	double z;
    	
    	FractionalBlockPos(BlockPos start) {
    		x = (double)start.getX() + 0.5;
    		y = (double)start.getY() + 0.5;
    		z = (double)start.getZ() + 0.5;
    	}
    	
    	FractionalBlockPos(FractionalBlockPos copied) {
    		this.x = copied.x;
    		this.y = copied.y;
    		this.z = copied.z;
    	}
    	
    	BlockPos location() {
    		return new BlockPos(Math.floor(x),(int)y,Math.floor(z));
    	}
    
    }
    
}