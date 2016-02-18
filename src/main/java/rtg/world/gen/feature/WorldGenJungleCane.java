package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenJungleCane extends WorldGenerator 
{
	private int height;
	
    public WorldGenJungleCane(int h)
    {
    	height = h;
    }

	@Override
	public boolean generate(World world, Random rand, BlockPos blockPos)
	{
    	Block b;
    	while(blockPos.getY() > 0)
    	{
    		b = world.getBlockState(blockPos).getBlock();
    		if(!world.isAirBlock(blockPos) || b.isLeaves(world, blockPos))
    		{
    			break;
    		}
    		blockPos = blockPos.down();
    	}
    	
    	b = world.getBlockState(blockPos).getBlock();
    	if(b != Blocks.grass && b != Blocks.dirt)
    	{
    		return false;
    	}

    	int j, sx, sz, ra;
    	for(j = 0; j < 4; j++)
    	{
        	b = world.getBlockState(new BlockPos(j == 0 ? blockPos.getX() - 1 : j == 1 ? blockPos.getX() + 1 : blockPos.getX(), blockPos.getY(), j == 2 ? blockPos.getZ() - 1 : j == 3 ? blockPos.getZ() + 1 : blockPos.getZ())).getBlock();
        	if(b.getMaterial() != Material.ground && b.getMaterial() != Material.grass)
        	{
        		return false;
        	}
    	}
    	
    	for(j = 0; j < 4; j++)
    	{
    		sx = j == 0 ? blockPos.getX() - 1 : j == 1 ? blockPos.getX() + 1 : blockPos.getX();
    		sz = j == 2 ? blockPos.getZ() - 1 : j == 3 ? blockPos.getZ() + 1 : blockPos.getZ();
    		ra = rand.nextInt(height * 2 + 1) + height;
    		
        	b = world.getBlockState(new BlockPos(sx, blockPos.getY() + 1, sz)).getBlock();
        	if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine)
        	{
        		for(int k = 0; k < ra; k++)
        		{
                	b = world.getBlockState(new BlockPos(sx, blockPos.getY() + 1 + k, sz)).getBlock();
                	if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine)
                	{
            			world.setBlockState(new BlockPos(sx, blockPos.getY() + 1 + k, sz), Blocks.reeds.getDefaultState(), 2);
                	}
                	else
                	{
                		break;
                	}
        		}
        	}
    	}

    	world.setBlockState(new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ()), Blocks.water.getDefaultState());
    	
    	return true;
	}
}
