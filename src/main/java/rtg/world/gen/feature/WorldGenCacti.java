package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCacti extends WorldGenerator
{
	private boolean sand;
	private int eHeight;
	private Block soilBlock;
	private byte soilMeta;
	
	public WorldGenCacti(boolean sandOnly)
	{
		this(sandOnly, 0);
	}
	
    public WorldGenCacti(boolean sandOnly, int extraHeight) {
    	this(sandOnly, extraHeight, Blocks.sand, (byte)0);
    }
	
	public WorldGenCacti(boolean sandOnly, int extraHeight, Block soilBlock, byte soilMeta)
	{
		sand = sandOnly;
		eHeight = extraHeight;
		this.soilBlock = soilBlock;
		this.soilMeta = soilMeta;
	}

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block b;
        //for (int l = 0; l < 10; ++l)
        {
            int i1 = x;// + rand.nextInt(8) - rand.nextInt(8);
            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            int k1 = z;// + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(i1, j1, k1))
            {
            	b = world.getBlock(i1, j1 - 1, k1);
            	if(b == Blocks.sand || (!sand && (b == Blocks.grass || b == Blocks.dirt)))
            	{
	                int l1 = 1 + rand.nextInt(rand.nextInt(3) + 1);
	                if(b == Blocks.grass || b == Blocks.dirt)
	                {
	                	world.setBlock(i1, j1 - 1, k1, Blocks.sand, 0, 2);
	                }
	
	                for (int i2 = 0; i2 < l1 + eHeight; ++i2)
	                {
	                    if (Blocks.cactus.canBlockStay(world, i1, j1 + i2, k1))
	                    {
	                    	world.setBlock(i1, j1 + i2, k1, Blocks.cactus, 0, 2);
	                    }
	                }
            	}
            }
        }

        return true;
    }
}