package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFlowers extends WorldGenerator
{
    private int[] flowers;

    /**
     *  FLOWER LIST: 
	 *
		0	Rose - 
		1	Blue Orchid - 
		2	Allium - 
		3	Azure Bluet - 
		4	Red Tulip - 
		5	Orange Tulip - 
		6	White Tulip - 
		7	Pink Tulip - 
		8	Oxeye Daisy - 
		
		9	yellow Flower - 
		
		10	Sunflower - 
		11	Lilac - 
		12	Double Tallgrass - 
		13	Large Fern - 
		14	Rose Bush - 
		15	Peony
     * 
     */
    public WorldGenFlowers(int[] f)
    {
    	flowers = f;
    }
    
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	int randomFlower = flowers[rand.nextInt(flowers.length)];
    	
    	if(randomFlower > 9)
    	{
            for (int l = 0; l < 64; ++l)
            {
                int i1 = x + rand.nextInt(8) - rand.nextInt(8);
                int j1 = y + rand.nextInt(4) - rand.nextInt(4);
                int k1 = z + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 254) && Blocks.double_plant.canPlaceBlockAt(world, i1, j1, k1))
                {
                    Blocks.double_plant.func_149889_c(world, i1, j1, k1, randomFlower - 10, 0);
                }
            }
    	}
    	else if(randomFlower == 9)
    	{
	        for (int l = 0; l < 64; ++l)
	        {
	            int i1 = x + rand.nextInt(8) - rand.nextInt(8);
	            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
	            int k1 = z + rand.nextInt(8) - rand.nextInt(8);
	
	            if (world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 255) && Blocks.yellow_flower.canBlockStay(world, i1, j1, k1))
	            {
	            	world.setBlock(i1, j1, k1, Blocks.yellow_flower, 0, 0);
	            }
	        }
    	}
    	else
    	{
	        for (int l = 0; l < 64; ++l)
	        {
	            int i1 = x + rand.nextInt(8) - rand.nextInt(8);
	            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
	            int k1 = z + rand.nextInt(8) - rand.nextInt(8);
	
	            if (world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 255) && Blocks.red_flower.canBlockStay(world, i1, j1, k1))
	            {
	            	world.setBlock(i1, j1, k1, Blocks.red_flower, randomFlower, 0);
	            }
	        }
    	}

        return true;
    }
}