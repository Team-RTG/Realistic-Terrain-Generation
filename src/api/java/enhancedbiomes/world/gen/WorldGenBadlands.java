package enhancedbiomes.world.gen;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.*;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBadlands extends WorldGenerator
{
	int radius;
	float variation = 1;
	Block stone;
	int stoneMeta;
	
	public WorldGenBadlands(int radius, float variation, Block stone, int meta)
	{
		this.radius = radius;
		this.variation = variation;
		this.stone = stone;
		this.stoneMeta = meta;
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
						for(int gY = getTopStoneBlock(posX + baseX, posZ + baseZ, par1World); gY <= posY + baseY; gY++)
						{
							par1World.setBlock(posX + baseX, gY, posZ + baseZ, stone, stoneMeta, 3);
						}
					}
				}
			}
		}
		
		return true;
	}
}
