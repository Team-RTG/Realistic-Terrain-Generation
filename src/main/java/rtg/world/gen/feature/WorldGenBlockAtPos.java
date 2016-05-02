package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.util.Logger;

/**
 * Quick, dirty, hacky worldgen I created to test for double decorations.
 * 
 * @author WhichOnesPink
 *
 */
public class WorldGenBlockAtPos extends WorldGenerator
{

	protected Block block;
	protected Logger logger;
	
	public WorldGenBlockAtPos(Block block)
	{
		this.block = block;
		this.logger = new Logger();
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		Block b = world.getBlock(x, 240, z);
		
		if (b.getMaterial() == Material.air) {
			world.setBlock(x, 240, z, this.block, 0, 2);
			this.logger.info("Block placed at %d 240 %d", x, z);
		}
		else {
			world.setBlock(x, 239, z, this.block, 0, 2);
			this.logger.info("Block placed at %d 239 %d", x, z);
		}

		return true;
	}
}
