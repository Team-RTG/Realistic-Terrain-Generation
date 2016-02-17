package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
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
    
    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
    	int randomFlower = flowers[rand.nextInt(flowers.length)];
    	
    	if(randomFlower > 9)
    	{
            for (int l = 0; l < 64; ++l)
            {
                int i1 = blockPos.getX() + rand.nextInt(8) - rand.nextInt(8);
                int j1 = blockPos.getY() + rand.nextInt(4) - rand.nextInt(4);
                int k1 = blockPos.getZ() + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && (!world.provider.getHasNoSky() || j1 < 254) && Blocks.double_plant.canPlaceBlockAt(world, new BlockPos(i1, j1, k1)))
                {
                    Blocks.double_plant.placeAt(world, new BlockPos(i1, j1, k1), BlockDoublePlant.EnumPlantType.byMetadata(randomFlower - 10), 0);
                }
            }
    	}
    	else if(randomFlower == 9)
    	{
	        for (int l = 0; l < 64; ++l)
	        {
	            int i1 = blockPos.getX() + rand.nextInt(8) - rand.nextInt(8);
	            int j1 = blockPos.getY() + rand.nextInt(4) - rand.nextInt(4);
	            int k1 = blockPos.getZ() + rand.nextInt(8) - rand.nextInt(8);

				if (world.isAirBlock(new BlockPos(i1, j1, k1)) && (!world.provider.getHasNoSky() || j1 < 255) && Blocks.yellow_flower.canBlockStay(world, new BlockPos(i1, j1, k1), world.getBlockState(new BlockPos(i1, j1, k1))))
				{
	            	world.setBlockState(new BlockPos(i1, j1, k1), Blocks.yellow_flower.getDefaultState(), 0);
	            }
	        }
    	}
    	else
    	{
	        for (int l = 0; l < 64; ++l)
	        {
	            int i1 = blockPos.getX() + rand.nextInt(8) - rand.nextInt(8);
	            int j1 = blockPos.getY() + rand.nextInt(4) - rand.nextInt(4);
	            int k1 = blockPos.getZ() + rand.nextInt(8) - rand.nextInt(8);

				if (world.isAirBlock(new BlockPos(i1, j1, k1)) && (!world.provider.getHasNoSky() || j1 < 255) && Blocks.red_flower.canBlockStay(world, new BlockPos(i1, j1, k1), world.getBlockState(new BlockPos(i1, j1, k1))))
	            {
	            	world.setBlockState(new BlockPos(i1, j1, k1), Blocks.red_flower.getStateFromMeta(randomFlower), 0);
	            }
	        }
    	}

        return true;
    }
}