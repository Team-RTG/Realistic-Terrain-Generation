package enhancedbiomes.world.gen.geometry;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCylinder extends WorldGenerator
{
	Block fillId = Blocks.air;
	int radius;
	int height;
	
	public WorldGenCylinder(Block fill, int radius, int height)
	{
		this.fillId = fill;
		this.radius = radius;
		this.height = height;
	}
	
	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z) 
	{
		int baseX = x;
		int baseY = y;
		int baseZ = z;

		int radiusSq = radius * radius;

		for (int varY = 0; varY < height; varY++)
		{
			for (int posX = -radius; posX <= radius; posX++) 
			{
				for (int posZ = -radius; posZ <= radius; posZ++) 
				{
					int distance = posX * posX + posZ * posZ;

					if (distance <= radiusSq) 
					{
						par1World.setBlock(posX + baseX, varY + baseY, posZ + baseZ, this.fillId, 0, 3);
					}
				}
			}
		}
		
		return true;
	}
}
