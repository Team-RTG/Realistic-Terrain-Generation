package rtg.world.gen.feature;

import java.util.Random;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.TerrainMath;
import net.minecraftforge.fml.common.registry.GameData;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenVolcano 
{
    protected static Block volcanoBlock = Block.getBlockFromName(ConfigRTG.volcanoBlockId);
    protected static byte volcanoByte = (byte) ConfigRTG.volcanoBlockByte;
    protected static Block lavaBlock = ConfigRTG.enableVolcanoEruptions ? Blocks.flowing_lava : Blocks.lava;
    
	public static void build(Block[] blocks, byte[] metadata, World world, Random mapRand, int baseX, int baseY, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float[] noise)
	{	
	    return;
	    
//		int i, j;
//		float distance, height, obsidian;
//		Block b;
//		byte meta;
//		
//		for (int x = 0; x < 16; x++)
//		{
//			for (int z = 0; z < 16; z++)
//			{
//				i = (chunkX * 16) + x;
//				j = (chunkY * 16) + z;
//				
//				distance = (float)TerrainMath.dis2(i, j, baseX * 16, baseY * 16);
//				obsidian = 24f + distance + simplex.noise2(i / 16f, j / 16f) * 15f;
//				
//				if(distance < 10 + simplex.noise2(i / 3f, j / 3f) * 1.5f)
//				{
//					height = simplex.noise2(i / 5f, j / 5f) * 2f;
//					for (int y = 255; y > -1; y--)
//					{
//						if(y > 165)
//						{
//							if(blocks[cta(x, y, z)] != Blocks.air)
//							{
//								blocks[cta(x, y, z)] = Blocks.air;
//							}
//						}
//						else if(y > obsidian && y < 156 + height)
//						{
//						    blocks[cta(x, y, z)] = volcanoBlock;
//						    metadata[cta(x, y, z)] = volcanoByte;
//							
//							
//						}
//						else if(y < 166)
//						{
//							blocks[cta(x, y, z)] = lavaBlock;
//						}
//						else if(y < obsidian + 1)
//						{
//							if(blocks[cta(x, y, z)] == Blocks.air)
//							{
//							    blocks[cta(x, y, z)] = Blocks.stone;
//							    metadata[cta(x, y, z)] = (byte)0;
//							}
//							else
//							{
//								break;
//							}
//						}
//					}
//				}
//				else
//				{
//					height = 190f - (distance + simplex.noise2(i / 12f, j / 12f) * 5f) * 1.7f;
//					if(height > noise[x * 16 + z])
//					{
//						noise[x * 16 + z] = height;
//					}
//					
//					for (int y = 255; y > -1; y--)
//					{
//						if(y <= height)
//						{
//							b = blocks[cta(x, y, z)];
//							meta = metadata[cta(x, y, z)];
//							
//							if(b == Blocks.air)
//							{
//								if(y > obsidian)
//								{
//									b = volcanoBlock;
//									meta = volcanoByte;
//								}
//								else
//								{
//									b = Blocks.stone;
//									meta = (byte)0;
//								}
//							}
//							else
//							{
//								break;
//							}
//							
//							blocks[cta(x, y, z)] = b;
//							metadata[cta(x, y, z)] = meta;
//						}
//					}
//				}
//			}
//		}
	}
	
    public static int cta(int x, int y, int z)
    {
    	return (x * 16 + z) * 256 + y;
    }
}