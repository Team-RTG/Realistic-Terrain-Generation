package enhancedbiomes.world.gen.geometry;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSphereIfEmpty extends WorldGenerator
{
	Block fillId = Blocks.air;
	int radius;
	boolean exception = false;
	Block exceptionID = Blocks.air;
	int meta = 0;
	
	public WorldGenSphereIfEmpty(Block fill, int meta, int radius)
	{
		this.fillId = fill;
		this.radius = radius;
		this.meta = meta;
	}
	
	public WorldGenSphereIfEmpty(Block fill, int radius, boolean exception, Block exceptionID)
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
							setBlockIfEmpty(posX + baseX, posY + baseY, posZ + baseZ, this.fillId, this.meta, 3, par1World);
						}
					}
				}
			}
		}
		
		return true;
	}
}
