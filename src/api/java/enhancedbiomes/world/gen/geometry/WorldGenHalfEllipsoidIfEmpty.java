package enhancedbiomes.world.gen.geometry;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHalfEllipsoidIfEmpty extends WorldGenerator
{
	Block fillId = Blocks.air;
	int radius;
	boolean exception = false;
	Block exceptionID = Blocks.air;
	int fillMeta = 0;
	float variation = 1;
	
	public WorldGenHalfEllipsoidIfEmpty(Block fill, int radius, float variation)
	{
		this.fillId = fill;
		this.radius = radius;
		this.variation = variation;
	}
	
	public WorldGenHalfEllipsoidIfEmpty(Block fill, int radius, float variation, int meta)
	{
		this.fillId = fill;
		this.radius = radius;
		this.fillMeta = meta;
		this.variation = variation;
	}
	
	public WorldGenHalfEllipsoidIfEmpty(Block fill, int radius, float variation, boolean exception, Block exceptionID)
	{
		this.fillId = fill;
		this.radius = radius;
		this.exception = exception;
		this.exceptionID = exceptionID;
		this.variation = variation;
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
			for (int posY = 0; posY <= radius * variation; posY++) 
			{
				for (int posZ = -radius; posZ <= radius; posZ++) 
				{
					int distance = (int) (posX * posX + posY * posY / variation / variation + posZ * posZ);

					if (distance <= radiusSq) 
					{
						if(this.exception == true && par1World.getBlock(posX + baseX, posY + baseY, posZ + baseZ) == this.exceptionID)
						{
							
						}
						else
						{
							setBlockIfEmpty(posX + baseX, posY + baseY, posZ + baseZ, this.fillId, this.fillMeta, 3, par1World);
						}
					}
				}
			}
		}
		
		return true;
	}
}
