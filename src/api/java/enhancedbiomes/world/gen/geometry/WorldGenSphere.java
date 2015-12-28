package enhancedbiomes.world.gen.geometry;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSphere extends WorldGenerator
{
	Block fillId = Blocks.air;
	int radius;
	boolean exception = false;
	Block exceptionID = Blocks.air;
	
	public WorldGenSphere(Block fill, int radius)
	{
		this.fillId = fill;
		this.radius = radius;
	}
	
	public WorldGenSphere(Block fill, int radius, boolean exception, Block exceptionID)
	{
		this.fillId = fill;
		this.radius = radius;
		this.exception = exception;
		this.exceptionID = exceptionID;
	}
	
	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z) 
	{
		int baseX = x;
		int baseY = y;
		int baseZ = z;

		int radiusSq = radius * radius;

		for (int posX = -radius; posX <= radius; posX++) 
		{
			for (int posY = -radius; posY <= radius; posY++) 
			{
				for (int posZ = -radius; posZ <= radius; posZ++) 
				{
					int distance = posX * posX + posY * posY + posZ * posZ;

					if (distance <= radiusSq) 
					{
						if(par1World.getBlock(posX + baseX, posY + baseY, posZ + baseZ) == this.exceptionID && this.exception == true)
						{
							
						}
						else
						{
							par1World.setBlock(posX + baseX, posY + baseY, posZ + baseZ, this.fillId, 0, 3);
						}
					}
				}
			}
		}
		
		return true;
	}
}
