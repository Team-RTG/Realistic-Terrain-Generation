
package rtg.world.gen.feature;

/**
 *
 * @author Zeno410
 */

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPlantBlock extends WorldGenerator
{
	private Block soilBlock;
	private byte soilMeta;
    private Block plantBlock;



	public WorldGenPlantBlock(Block plantBlock)
	{
        this.plantBlock = plantBlock;
	}

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block b;
        //for (int l = 0; l < 10; ++l)
        {
            int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(i1, j1, k1)||world.getBlock(x, y, z).isLeaves(world, x, y, z))
            {
            	b = world.getBlock(i1, j1 - 1, k1);
            	if(b == Blocks.grass || b == Blocks.dirt)
            	{
                    if (plantBlock.canBlockStay(world, i1, j1 , k1))
                    {
                        world.setBlock(i1, j1, k1,plantBlock, 0, 2);
                    }
                }
            }
        }

        return true;
    }
}