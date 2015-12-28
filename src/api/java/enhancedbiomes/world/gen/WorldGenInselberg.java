package enhancedbiomes.world.gen;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.*;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.EnhancedBiomesWorldHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenInselberg extends WorldGenerator
{
	int radius;
	boolean exception = false;
	Block exceptionID = Blocks.air;
	float variation = 1;
	Block stone;
	int stoneMeta;
	
	public WorldGenInselberg(int radius, float variation, Block stone, int meta)
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
			for (int posZ = -radius; posZ <= radius; posZ++) 
			{
				int distance = (int) (posX * posX + posZ * posZ);
				
				if (distance <= radiusSq) {					
					int lowY = EnhancedBiomesWorldHelper.getTopStoneBlock(posX + baseX, posZ + baseZ, par1World);
					
					for (int posY = baseY - 1; posY >= lowY; posY--) 
					{
						setBlockIfNotStone(posX + baseX, posY, posZ + baseZ, stone, stoneMeta, 3, par1World);
					}
				}
			}
		}
		
		//Base Hill
		for (int posX = -radius; posX <= radius; posX++) 
		{
			for (int posY = 0; posY <= radius * variation; posY++) 
			{
				for (int posZ = -radius; posZ <= radius; posZ++) 
				{
					int distance = (int) (posX * posX + posY * posY / variation / variation + posZ * posZ);
					int distance1 = (int) (posX * posX + (posY + 1) * (posY + 1) / variation / variation + posZ * posZ);
					int distance3 = (int) (posX * posX + (posY + 3) * (posY + 3) / variation / variation + posZ * posZ);

					if (distance <= radiusSq) 
					{
						BiomeGenBase biome = par1World.getBiomeGenForCoords(posX + baseX, posZ + baseZ);
						if(distance1 > radiusSq)
						{
							if(biome.topBlock == Blocks.grass)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.grassList[biome.biomeID], EnhancedBiomesMod.grassMetaList[biome.biomeID], 3, par1World);
							}
							else if(biome.topBlock == Blocks.dirt)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.soilList[biome.biomeID], EnhancedBiomesMod.soilMetaList[biome.biomeID], 3, par1World);
							}
							else
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, biome.topBlock, biome.field_150604_aj, 3, par1World);
							}							
						}
						else if(distance3 > radiusSq)
						{
							if(biome.fillerBlock == Blocks.grass)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.grassList[biome.biomeID], EnhancedBiomesMod.grassMetaList[biome.biomeID], 3, par1World);
							}
							else if(biome.fillerBlock == Blocks.dirt)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.soilList[biome.biomeID], EnhancedBiomesMod.soilMetaList[biome.biomeID], 3, par1World);
							}
							else
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, biome.fillerBlock, 0, 3, par1World);
							}
						}
						else
						{
							setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, stone, stoneMeta, 3, par1World);
						}
					}
				}
			}
		}
		
		//Inselberg
		baseY = getTopStoneBlock(baseX, baseZ, par1World) - 2;

		radius /= 2;
		variation *= 2;
		radiusSq = radius * radius;

		for (int posX = -radius; posX <= radius; posX++) 
		{
			for (int posY = 0; posY <= radius * variation; posY++) 
			{
				for (int posZ = -radius; posZ <= radius; posZ++) 
				{
					int distance = (int) (posX * posX + posY * posY / variation / variation + posZ * posZ);
					int distance1 = (int) (posX * posX + (posY + 1) * (posY + 1) / variation / variation + posZ * posZ);
					int distance2 = (int) (posX * posX + (posY + 2) * (posY + 2) / variation / variation + posZ * posZ);

					if (distance <= radiusSq) 
					{
						BiomeGenBase biome = par1World.getBiomeGenForCoords(posX + baseX, posZ + baseZ);
						if(distance1 > radiusSq)
						{
							if(biome.topBlock == Blocks.grass)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.grassList[biome.biomeID], EnhancedBiomesMod.grassMetaList[biome.biomeID], 3, par1World);
							}
							else if(biome.topBlock == Blocks.dirt)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.soilList[biome.biomeID], EnhancedBiomesMod.soilMetaList[biome.biomeID], 3, par1World);
							}
							else
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, biome.topBlock, biome.field_150604_aj, 3, par1World);
							}
						}
						else if(distance2 > radiusSq)
						{
							if(biome.fillerBlock == Blocks.grass)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.grassList[biome.biomeID], EnhancedBiomesMod.grassMetaList[biome.biomeID], 3, par1World);
							}
							else if(biome.fillerBlock == Blocks.dirt)
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, EnhancedBiomesMod.soilList[biome.biomeID], EnhancedBiomesMod.soilMetaList[biome.biomeID], 3, par1World);
							}
							else
							{
								setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, biome.fillerBlock, 0, 3, par1World);
							}
						}
						else
						{
							setBlockIfNotStone(posX + baseX, posY + baseY, posZ + baseZ, stone, stoneMeta, 3, par1World);
						}
					}
				}
			}
		}
		
		return true;
	}
}
