package enhancedbiomes.world.gen.geometry;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenConeVariation extends WorldGenerator
{
	Block fillId = Blocks.air;
	int radius;
	int height;
	int dx;
	int dz;
	
	public WorldGenConeVariation(Block fill, int radius, int height, int dx, int dz)
	{
		this.fillId = fill;
		this.radius = radius;
		this.height = height;
		this.dx = dx;
		this.dz = dz;
	}
	
	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z) 
	{
		double radiusSq = radius * radius;

		for (int posX =  -radius; posX <=  radius; posX++) 
		{
			for (int posZ = -radius; posZ <= radius; posZ++) 
			{
				int distance = posX * posX + posZ * posZ;

				if (distance <= radiusSq) 
				{
					for(int b = 0; b < height; b++)
					{
						double ax = (double) posX - dx / ((double) height) * ((double) height - b);
						double az = (double) posZ - dz / ((double) height) * ((double) height - b);
						System.out.println(ax + ", " + az);
						
						par1World.setBlock(x + (int) ax, b + y - 1, z + (int) az, this.fillId, 0, 3);
					}
				}
			}
		}
		
		/*for(int b = 1; b <= height; b++)
		{
			double i = (double) radius / ((double) height) * ((double) height - b);
			
			double radiusSq = i * i;

			for (int posX = (int) -i - 1; posX <= (int) i + 1; posX++) 
			{
				for (int posZ = (int) -i - 1; posZ <= (int) i + 1; posZ++) 
				{
					int distance = posX * posX + posZ * posZ;

					if (distance <= radiusSq) 
					{
						par1World.setBlock(posX + x, b + y - 1, posZ + z, this.fillId, 0, 3);
					}
				}
			}
		}*/
		
		return true;
	}
}
