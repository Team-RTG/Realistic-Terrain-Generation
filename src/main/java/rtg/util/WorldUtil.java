package rtg.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldUtil
{
	private World world;
	
	public WorldUtil(World world)
	{
		this.world = world;
	}
	
	/**
	 * Checks a given coordinate to see if it is surrounded by a given block, usually air.
	 * This method only checks along the same Y coord.
	 */
	public boolean isSurroundedByBlock(Block checkBlock, int checkDistance, SurroundCheckType checkType, Random rand, int x, int y, int z)
	{
		switch (checkType)
		{
			case FULL: // Checks the entire radius around the coord.
				
				for (int ix = -checkDistance; ix <= checkDistance; ix++) {
					for (int iz = -checkDistance; iz <= checkDistance; iz++) {
						
						if (x == ix && z == iz) continue;
						
						if (this.world.getBlock(x + ix, y, z + iz) != checkBlock) return false;
					}
				}

				break;
				
			case CARDINAL: // Checks the N/E/S/W directions around the coord.
				
				for (int i = checkDistance; i > 0; i--) {
					
					if (this.world.getBlock(x, y, z + i) != checkBlock) return false;
					if (this.world.getBlock(x, y, z - i) != checkBlock) return false;
					if (this.world.getBlock(x + i, y, z) != checkBlock) return false;
					if (this.world.getBlock(x - i, y, z) != checkBlock) return false;
				}
				
				break;
				
			case ORDINAL: // Checks the NE/SE/SW/NW directions around the coord.
				
				for (int i = checkDistance; i > 0; i--) {
					
					if (this.world.getBlock(x + i, y, z + i) != checkBlock) return false;
					if (this.world.getBlock(x + i, y, z - i) != checkBlock) return false;
					if (this.world.getBlock(x - i, y, z + i) != checkBlock) return false;
					if (this.world.getBlock(x - i, y, z - i) != checkBlock) return false;
				}
				
				break;
				
			default:
				break;
		}
		
		return true;
	}
	
	public enum SurroundCheckType
	{
		FULL,
		CARDINAL,
		ORDINAL
	}
}