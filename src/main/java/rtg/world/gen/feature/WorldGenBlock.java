package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBlock extends WorldGenerator
{
	protected Block placeBlock;
	protected byte placeBlockMeta;
	protected Block replaceBlock;
	protected byte replaceBlockMeta;
	protected Block adjacentBlock;
	protected byte adjacentBlockMeta;
	protected int minAdjacents;

    public WorldGenBlock(Block placeBlock, byte placeBlockMeta, Block replaceBlock, byte replaceBlockMeta, Block adjacentBlock, byte adjacentBlockMeta, int minAdjacents)
    {
        super(false);

    	this.placeBlock = placeBlock;
    	this.placeBlockMeta = placeBlockMeta;
    	this.replaceBlock = replaceBlock;
    	this.replaceBlockMeta = replaceBlockMeta;
    	this.adjacentBlock = adjacentBlock;
    	this.adjacentBlockMeta = adjacentBlockMeta;
    	this.minAdjacents = minAdjacents;
    }

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		Block targetBlock = world.getBlock(x, y, z);
		
		if (targetBlock != replaceBlock) {
			//Logger.debug("Target block (%s) does not equal Replace block (%s)", targetBlock.getLocalizedName(), replaceBlock.getLocalizedName());
			return false;
		}
		
		if (!isAdjacent(world, x, y, z)) {
			//Logger.debug("Target block (%s) is not adjacent to %s", targetBlock.getLocalizedName(), this.adjacentBlock.getLocalizedName());
			return false;
		}
		
        world.setBlock(x, y, z, placeBlock, placeBlockMeta, 2);
        
        //Logger.debug("COBWEB at %d, %d, %d !!!", x, y, z);
        
        return true;
	}
	
	protected boolean isAdjacent(World world, int x, int y, int z)
	{
		int adjacentCount = 0;
		
		if (world.getBlock(x + 1, y, z) == this.adjacentBlock) {
			adjacentCount++;
		}

		if (world.getBlock(x - 1, y, z) == this.adjacentBlock) {
			adjacentCount++;
		}
		
		if (world.getBlock(x, y + 1, z) == this.adjacentBlock) {
			adjacentCount++;
		}
		
		if (world.getBlock(x, y - 1, z) == this.adjacentBlock) {
			adjacentCount++;
		}
		
		if (world.getBlock(x, y, z + 1) == this.adjacentBlock) {
			adjacentCount++;
		}
		
		if (world.getBlock(x, y, z - 1) == this.adjacentBlock) {
			adjacentCount++;
		}

		return (adjacentCount > 0 && adjacentCount >= this.minAdjacents);
	}
}