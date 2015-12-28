package enhancedbiomes.world.gen.geometry;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCone extends WorldGenerator
{
	Block fillId = Blocks.air;
	double radius;
	int height;
	int meta = 0;
	
	public WorldGenCone(Block fill, double radius, int height)
	{
		this.fillId = fill;
		this.radius = radius;
		this.height = height;
	}
	
	public WorldGenCone(Block fill, int meta, double radius, int height)
	{
		this(fill, radius, height);
		this.meta = meta;
	}
	
	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z) 
	{
		for(int b = 1; b <= height; b++)
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
						par1World.setBlock(posX + x, b + y - 1, posZ + z, this.fillId, meta, 3);
					}
				}
			}
		}
		
		return true;
	}
}
